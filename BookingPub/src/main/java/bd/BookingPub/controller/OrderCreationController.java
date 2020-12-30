package bd.BookingPub.controller;
import bd.BookingPub.model.Ordering;
import bd.BookingPub.model.TableList;
import bd.BookingPub.services.OrderService.OrderingService;
import bd.BookingPub.services.TableListService.TableListService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/newOrder")
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationController {

    @Autowired
    OrderingService orderingService;

    @GetMapping("add")
    public String addPage(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "order-add";
    }

    @PostMapping("add")
    public String addOrder(@RequestParam(value = "placeNum") Integer placeNum,
                           @RequestParam(value = "orderExtraDetails") String orderExtraDetails,
                           @RequestParam(value = "contact") Long contact,
                           @RequestParam(value = "bookedForTime") String bookedForTime,

                      Model model
    ) {
        if(orderingService.addOrder(placeNum, orderExtraDetails, contact, bookedForTime)!=null)
            return "success";
        else
            return "fail";
    }





}
