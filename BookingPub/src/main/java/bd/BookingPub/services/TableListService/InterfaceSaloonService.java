package bd.BookingPub.services.TableListService;


import bd.BookingPub.model.TableList;

import java.util.List;
import java.util.UUID;

public interface InterfaceSaloonService {

    TableList addTable(TableList tableList);

    List<TableList> getAll();

    TableList getById(Integer id);

    String getStatus(Integer id);

    void changeStatus(Integer id, String status);

    List<TableList> getByPlaceNum(Integer placeNum);

    void deleteSaloonById(Integer id);
}
