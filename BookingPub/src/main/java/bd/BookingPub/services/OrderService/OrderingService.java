package bd.BookingPub.services.OrderService;

import bd.BookingPub.model.Ordering;
import bd.BookingPub.model.TableList;
import bd.BookingPub.repository.OrderRepository;
import bd.BookingPub.repository.TableListRepository;
import bd.BookingPub.services.TableListService.TableListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderingService implements InterfaceOrderingService {
    @Autowired
    private OrderRepository orderingRepository;
    @Autowired
    private TableListService tableListService;
    @Autowired
    private TableListRepository tableListRepository;


    public Ordering addOrder(Integer placeNum, String orderExtraDetails, Long contact, String bookedForTime) {
        Ordering ordering = new Ordering();
        ordering.setContact(contact);
        ordering.setOrderExtraDetails(orderExtraDetails);
        ordering.setPlaceNum(placeNum);
        ordering.setBookedForTime(bookedForTime);
        ordering.setStatus("Unconfirmed");
        List<TableList> tables = tableListService.getAll();
            for (TableList t :
                    tables)
            {
                if(placeNum<=t.getPlaceNum()&&t.getStatus().equals("Unbooked"))
                {
                    ordering.setTableList(t);
                    Ordering savedOrdering = orderingRepository.save(ordering);
                    t.setOrdering(ordering);
                    t.setStatus("Prebooked");
                    TableList table = tableListRepository.save(t);
                    return getById(savedOrdering.getOrderId());
                }
            }
        return null;
    }

    public List<Ordering> getAll() {
        return orderingRepository.findAll();
    }

    public Ordering getById(Integer id) {
        Ordering ordering = orderingRepository.findById(id).get();
        return ordering;
    }



    public String ChangeStatusTrue(Integer id)
    {
        Ordering ordering = orderingRepository.findById(id).get();
        ordering.setStatus("Confirmed");
        TableList t =ordering.getTableList();
        t.setStatus("Booked");
        Ordering savedOrdering = orderingRepository.save(ordering);
        TableList table = tableListRepository.save(t);
        return ordering.getStatus();
    }

    public void deleteOrderById(Integer id) {
        Ordering ordering = orderingRepository.findById(id).get();
        TableList t =ordering.getTableList();
        t.setStatus("Unbooked");
        orderingRepository.deleteById(id);
    }
}
