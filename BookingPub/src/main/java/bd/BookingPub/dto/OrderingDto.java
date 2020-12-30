package bd.BookingPub.dto;
import bd.BookingPub.model.TableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public final class OrderingDto {

    private UUID orderId;
    private int placeNum;
    private TableList tableList;
    private String orderExtraDetails;
    private String contact;
    private OffsetTime bookedForTime;
}
