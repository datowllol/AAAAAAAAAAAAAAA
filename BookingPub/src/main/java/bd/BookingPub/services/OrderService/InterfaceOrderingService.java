package bd.BookingPub.services.OrderService;



import bd.BookingPub.model.Ordering;
import bd.BookingPub.model.TableList;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;


public interface InterfaceOrderingService {

    Ordering addOrder(Integer placeNum, String orderExtraDetails, Long contact, String bookedForTime);

    List<Ordering> getAll();

    Ordering getById(Integer id);

    String ChangeStatusTrue(Integer id);


    void deleteOrderById(Integer id);
}
