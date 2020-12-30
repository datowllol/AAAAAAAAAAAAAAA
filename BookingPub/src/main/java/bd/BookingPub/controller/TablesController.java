package bd.BookingPub.controller;


import bd.BookingPub.model.Admin;
import bd.BookingPub.model.TableList;
import bd.BookingPub.services.OrderService.InterfaceOrderingService;
import bd.BookingPub.services.TableListService.TableListService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/tableList")
@AllArgsConstructor
@NoArgsConstructor
public class TablesController {
    @Autowired
    TableListService tableListService;

    @GetMapping()
    public String all(Model model, @AuthenticationPrincipal Admin user) {
        List<TableList> tableList = tableListService.getAll();
        model.addAttribute("tableList", tableList);
        return "tableList";
    }

    @GetMapping("/{tableId}")
    public String byId(@PathVariable Integer id, Model model, @AuthenticationPrincipal Admin user) {
        TableList table = tableListService.getById(id);
        model.addAttribute("table", table);
        return "table-info";
    }

    @GetMapping("add/kk")
    public String addPage(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "add-tableList";
    }

    @PostMapping("add/kk")
    public String add(@RequestParam(value = "placeNum") Integer placeNum,
                      Model model
    ) {
        if(placeNum!=null)
        {TableList table = new TableList();
        table.setStatus("Unbooked");
        table.setPlaceNum(placeNum);
        tableListService.addTable(table);}
        return "redirect:/tableList";
    }

    @GetMapping("/delete/table")
    public String addDeletePage(Model model) {
        model.addAttribute("isGODADMIN", true);
        return "delete-tableList";
    }

    @PostMapping("delete/table")
    public String delete(@RequestParam(value = "id") Integer id,
                         Model model) {
        model.addAttribute("isGODADMIN", true);
        if(id!=null) {
            TableList table = tableListService.getById(id);
            if (table.getStatus().equals("Unbooked")) {
                tableListService.deleteSaloonById(id);
                return "redirect:/tableList";
            }
        }
        return "redirect:/tableList";
    }

}