package bd.BookingPub.dto;

import bd.BookingPub.model.Ordering;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class TableListDto {

    private UUID tableId;
    private int placeNum;
    private String status;
    Ordering ordering;

}
