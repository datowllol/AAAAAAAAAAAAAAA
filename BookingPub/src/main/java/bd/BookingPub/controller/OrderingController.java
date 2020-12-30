package bd.BookingPub.controller;

import bd.BookingPub.model.Admin;
import bd.BookingPub.model.Ordering;
import bd.BookingPub.model.TableList;
import bd.BookingPub.services.OrderService.OrderingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/orderingMenu")
@AllArgsConstructor
@NoArgsConstructor
public class OrderingController {


    @Autowired
    OrderingService orderingService;

    @GetMapping()
    public String all(Model model, @AuthenticationPrincipal Admin user) {
        List<Ordering> orderings = orderingService.getAll();
        model.addAttribute("orderings", orderings);
        return "orderingMenu";
    }


    @GetMapping("confirm")
    public String addPageConfrim(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "confirm-order";
    }

    @PostMapping("confirm")
    public String confrim(@RequestParam(value = "id") Integer id,
                         Model model) {
        model.addAttribute("isGODADMIN", true);
        orderingService.ChangeStatusTrue(id);
        return "redirect:/orderingMenu";
    }

    @GetMapping("unconfirm")
    public String addPage(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "delete-order";
    }


    @PostMapping("unconfirm")
    public String delete(@RequestParam(value = "id") Integer id,
                         Model model) {
        model.addAttribute("isGODADMIN", true);
        orderingService.deleteOrderById(id);
        return "redirect:/orderingMenu";
    }

}
