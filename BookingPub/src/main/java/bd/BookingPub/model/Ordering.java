package bd.BookingPub.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column
    private int placeNum;

    @Column
    private String orderExtraDetails;

    @Column
    private Long contact;

    @Column
    private String bookedForTime;

    @Column
    private String status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "tableId")
    private TableList tableList;

}
