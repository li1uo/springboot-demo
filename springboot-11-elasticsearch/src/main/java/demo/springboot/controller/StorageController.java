package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.dao.StorageRepository;
import demo.springboot.model.Storage;
import demo.springboot.model.StoragePageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author LILUO
 * @date 2020/02/01
 */
@Slf4j
@AllArgsConstructor
@RequestMapping("/storage")
@RestController
public class StorageController {

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private StorageRepository storageRepository;

    /**
     * 创建索引(此方式不会自动创建mapping)
     *
     * @return
     */
    @PostMapping("/index/create")
    public Result createIndex(){
        return Result.status(elasticsearchRestTemplate.createIndex(Storage.class));
    }

    /**
     * 删除索引
     *
     * @return
     */
    @PostMapping("/index/delete")
    public Result deleteIndex(){
        return Result.status(elasticsearchRestTemplate.deleteIndex(Storage.class));
    }

    /**
     * 分页查询
     *
     * @param pageDto
     * @return
     */
    @GetMapping("/list")
    public Result<Page> list(StoragePageDto pageDto){
        QueryBuilder queryBuilder;
        SearchQuery searchQuery;

        Pageable pageable = PageRequest.of(pageDto.getPageIndex(), pageDto.getPageSize());

        if (StringUtils.isBlank(pageDto.getSearchContent())){
            queryBuilder = QueryBuilders.matchAllQuery();
        }else{
            queryBuilder = QueryBuilders.multiMatchQuery(pageDto.getSearchContent(), "goodName", "merchantName");
        }
        // SortBuilder sortBuilder = SortBuilders.fieldSort("sales").order(SortOrder.DESC);
        searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();

        Page<Storage> page = elasticsearchRestTemplate.queryForPage(searchQuery, Storage.class);
        return Result.data(page);
    }

    /**
     * 查询文档数量
     *
     * @return
     */
    @GetMapping("/count")
    public Result<Long> count(){
        return Result.data(storageRepository.count());
    }

    /**
     * save为add 和 update
     *
     * @param storage
     * @return
     */
    @PostMapping("/save")
    public Result save(Storage storage){
        storage.setSales(0L);
        return Result.data(storageRepository.save(storage));
    }

    /**
     * 下单(扣库存)
     *
     * @param id
     * @return
     */
    @PostMapping("/order/{id}")
    public Result createOrder(@PathVariable Long id){
        /*Optional<Storage> optionalStorage = storageRepository.findById(id);
        optionalStorage.get();*/

        Storage storage = elasticsearchRestTemplate.queryForObject(GetQuery.getById(id.toString()), Storage.class);
        storage.setSales(storage.getSales() + 1);
        storageRepository.save(storage);
        return Result.data(storage);
    }

    /**
     * 移除库存
     *
     * @param id
     * @return
     */
    @PostMapping("/remove")
    public Result remove(Long id){
        storageRepository.deleteById(id);
        return Result.success();
    }

    /**
     * 查询所有库存
     *
     * @return
     */
    @GetMapping("/all")
    public Result<List<Storage>> all(){
        //List<Storage> list = IteratorUtils.toList(storageRepository.findAll().iterator());

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
        List<Storage> list = elasticsearchRestTemplate.queryForList(searchQuery, Storage.class);
        return Result.data(list);
    }

}
