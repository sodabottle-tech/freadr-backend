package com.sodabottle.freadr.models;

import com.sodabottle.freadr.enums.Status;
import com.sodabottle.freadr.enums.SwapGetType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Audited
@EqualsAndHashCode(of = {"fromUserId", "toUserId", "fromBookId", "toBookId"})
@NoArgsConstructor
@Table(name = "swap_request",
        indexes = {
                @Index(name = "idx_all", columnList = "from_user_id,to_user_id,from_book_id,to_book_id", unique = true),
                @Index(name = "idx_from_user_id", columnList = "from_user_id"),
                @Index(name = "idx_to_user_id", columnList = "to_user_id"),
                @Index(name = "idx_deleted", columnList = "deleted"),
                @Index(name = "idx_status", columnList = "status")})
public class SwapRequestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "from_book_id")
    private Long fromBookId;

    @Column(name = "to_book_id")
    private Long toBookId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private SwapGetType type;

    private boolean deleted;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "update_count")
    private int updateCount;
}
