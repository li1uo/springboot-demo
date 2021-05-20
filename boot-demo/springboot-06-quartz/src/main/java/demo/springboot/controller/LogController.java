package demo.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import demo.springboot.common.domain.Result;
import demo.springboot.domain.LogPageDto;
import demo.springboot.domain.ScheduleLog;
import demo.springboot.service.ILogService;
import demo.springboot.tool.support.Query;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LILUO
 * @date 2021/01/27
 */
@Slf4j
@AllArgsConstructor
@RequestMapping("/log")
@Controller
public class LogController {

    private ILogService logService;

    /**
     * 返回日志列表页面
     *
     * @return
     */
    @GetMapping("/page")
    public String page() {

        return "schedule_log";
    }

    /**
     * 获取日志列表分页数据
     *
     * @param query
     * @param logPageDto
     * @return
     */
    @SneakyThrows
    @GetMapping("/page/data")
    public @ResponseBody
    Result<IPage<ScheduleLog>> pageData(Query query, LogPageDto logPageDto) {
        String[] dateRate = StringUtils.split(logPageDto.getCreateTime(), "~");
        if (StringUtils.isNoneBlank(logPageDto.getCreateTime()) && dateRate.length == 2){
            logPageDto.setBeginTime(DateUtils.parseDate(StringUtils.trim(dateRate[0]), "yyyy-MM-dd HH:mm:ss"));
            logPageDto.setEndTime(DateUtils.parseDate(StringUtils.trim(dateRate[1]), "yyyy-MM-dd HH:mm:ss"));
        }

        return Result.page(logService.page(query.build(), logPageDto));
    }

    /**
     * 查询定时任务执行详情步骤信息
     *
     * @param id
     * @return
     */
    @GetMapping("/data/{id}")
    public @ResponseBody Result<String> getScheduleLogDetail(@PathVariable String id) {
        String log = logService.getById(id).getExecDetail();

        return Result.data(formatLog(log));
    }


    @SneakyThrows
    @GetMapping("/export")
    public void export(String idStr, HttpServletResponse response) {
        List<String> list = JSON.parseArray(new String(Base64.getDecoder().decode(idStr)), String.class);

        String zipName = "midware-him-log.zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=" + zipName);

        String suffix = ".txt";
        // 获取当前应用程序目录
        String currentDir = System.getProperty("user.dir");
        String tmpDir = currentDir + "/tmp";
        // 如果当前目录下不存在临时目录、生成临时目录
        File tmpDirFile = FileUtil.file(tmpDir);
        if (!tmpDirFile.exists()) {
            FileUtil.mkdir(tmpDir);
        }
        List<File> fileList = new ArrayList<>();
        for (String id : list) {
            ScheduleLog log = logService.getById(id);
            if (Objects.isNull(log)) {
                continue;
            }
            // 生成临时日志文件、向文件中追加执行日志内容
            File file = FileUtil.createTempFile(log.getTaskId() + "-" + id, suffix, FileUtil.file(tmpDir), true);
            FileUtil.appendString(log.getExecDetail(), file, Charset.forName("UTF-8"));
            fileList.add(file);
        }

        // 生成zip文件
        List<String> fileName = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<InputStream> inputStreamList = fileList.stream().map(file -> FileUtil.getInputStream(file)).collect(Collectors.toList());
        ZipUtil.zip(response.getOutputStream(), fileName.toArray(new String[0]), inputStreamList.toArray(new InputStream[0]));

        response.getOutputStream().close();

        // 删除所有的临时文件
        for (File file : fileList) {
            file.delete();
        }
    }

    /**
     * 处理数据库的日志 转成html格式
     *
     * @param log
     * @return
     */
    public String formatLog(String log) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div>");

        // 数据库的日志 \n分割, html上显示需要更换成<br>
        String[] logArray = log.split("\n");
        String end = "</p>";
        for (String str : logArray) {
            String begin = "<p>";

            if (str.contains("【INFO】") || str.contains("【 INFO】")) {

            }

            if (str.contains("【ERROR】")) {
                begin = "<p style='color:red'>";
            }
            stringBuilder.append(begin);
            stringBuilder.append(str);
            stringBuilder.append(end);
        }
        stringBuilder.append("</div>");

        return stringBuilder.toString();
    }
}
