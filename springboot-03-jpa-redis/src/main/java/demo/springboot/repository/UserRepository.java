package demo.springboot.repository;

import demo.springboot.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface UserRepository extends JpaRepository<UserDO,Long> {
}
