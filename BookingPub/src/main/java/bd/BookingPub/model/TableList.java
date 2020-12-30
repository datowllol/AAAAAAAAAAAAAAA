package bd.BookingPub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public final class TableList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tableId;

    @Column
    private int placeNum;

    @Column
    private String status;

    @OneToOne(mappedBy = "tableList")
    Ordering ordering;

}
