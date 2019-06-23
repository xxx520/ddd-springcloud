package org.yugh.authclient.ddd;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * //领域驱动开发 抽象公共对象
 *
 * @MappedSuperclass 父类公共字段注解，被继承的子类对象在保存时自动加载父类公有字段
 * <p>
 * @author: 余根海
 * @creation: 2019-04-05 21:50
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Data
@MappedSuperclass
public class AbstractEntity extends AbstractAggregateRoot implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    @Column(name = "id")
    private Long id;


    @Setter(AccessLevel.PRIVATE)
    @Column(name = "created_at")
    @Convert(converter = InstantLongConverter.class)
    private Instant createdAt;

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "updated_at")
    @Convert(converter = InstantLongConverter.class)
    private Instant updatedAt;

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "version")
    @Version
    private Integer version;


    @PrePersist
    public void prePersist() {
        setCreatedAt(Instant.now());
        setUpdatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(Instant.now());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + getId();
    }

}
