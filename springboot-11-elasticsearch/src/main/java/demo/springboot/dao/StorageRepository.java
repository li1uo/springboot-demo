package demo.springboot.dao;

import demo.springboot.model.Storage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author LILUO
 * @date 2020/02/01
 */
public interface StorageRepository extends ElasticsearchRepository<Storage, Long> {

}
