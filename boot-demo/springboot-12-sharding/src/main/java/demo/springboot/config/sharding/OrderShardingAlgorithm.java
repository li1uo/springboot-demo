package demo.springboot.config.sharding;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LILUO
 * @date 2020/09/04
 */
public class OrderShardingAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    private static final String BASE = "base_symbol";

    private static final String QUOTE = "quote_symbol";

    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<String> complexKeysShardingValue) {

        List<String> baseList = (List)complexKeysShardingValue.getColumnNameAndShardingValuesMap().get(BASE);
        String baseSymbol = baseList.get(0);

        List<String> quoteList = (List)complexKeysShardingValue.getColumnNameAndShardingValuesMap().get(QUOTE);
        String quoteSymbol = quoteList.get(0);

        List<String> route = new ArrayList<>();
        String tableName = complexKeysShardingValue.getLogicTableName() + "_" + baseSymbol + quoteSymbol;
        route.add(tableName);

        return route;
    }
}
