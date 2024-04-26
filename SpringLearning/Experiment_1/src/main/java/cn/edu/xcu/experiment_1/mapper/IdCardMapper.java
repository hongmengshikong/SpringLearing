package cn.edu.xcu.experiment_1.mapper;

import cn.edu.xcu.experiment_1.entity.IdCard;
import org.springframework.stereotype.Repository;

@Repository
public interface IdCardMapper {
    public IdCard findIdCardById(Long id);

}
