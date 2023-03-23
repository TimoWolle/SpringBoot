package training.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.entity.ToDo;

@Configuration
public class ModelMapperConfig {

    /**
     * Creates a ModelMapper which enables us to merge objects
     * This is mainly used to map the data transfer objects to the entities
     *
     * @return ModelMapper
     */

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}