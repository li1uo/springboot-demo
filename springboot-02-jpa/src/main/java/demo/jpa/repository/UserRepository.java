package demo.jpa.repository;

import demo.jpa.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface UserRepository extends JpaRepository<UserDO,Long> {
}
