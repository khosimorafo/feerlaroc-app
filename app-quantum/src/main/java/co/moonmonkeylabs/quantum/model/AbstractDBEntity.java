package co.moonmonkeylabs.quantum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feerlaroc.core.entity.EntityInterface;

import java.util.UUID;

public abstract class AbstractDBEntity implements EntityInterface {

    private String id;

    AbstractDBEntity(){
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public String id() {
        return id;
    }

}
