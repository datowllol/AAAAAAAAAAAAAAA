package bd.BookingPub.services.TableListService;


import bd.BookingPub.model.TableList;
import bd.BookingPub.repository.TableListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public final class TableListService implements InterfaceSaloonService {

    @Autowired
    private TableListRepository tableListRepository;


    public TableList addTable(TableList tableList) {
        TableList savedTableList = tableListRepository.save(tableList);
        return getById(savedTableList.getTableId());
    }

    public List<TableList> getAll() {
        return tableListRepository.findAll();
    }

    public TableList getById(Integer id) {
        TableList tableList = tableListRepository.findById(id).get();
        return tableList;
    }

    public void changeStatus(Integer id, String status) {
        TableList tableList = tableListRepository.findById(id).get();
        tableList.setStatus(status);
        deleteSaloonById(id);
        tableListRepository.save(tableList);
    }

    public String getStatus(Integer id)
    {
        TableList tableList = tableListRepository.findById(id).get();
        return tableList.getStatus();
    }

    public List<TableList> getByPlaceNum(Integer placeNum) {
        List<TableList> enoughPlaceTableList = new ArrayList<>();
        for (TableList t :
                tableListRepository.findAll()) {
            if (t.getPlaceNum() >= placeNum)
                enoughPlaceTableList.add(t);
        }
        return enoughPlaceTableList;
    }

    public void deleteSaloonById(Integer id) {
        tableListRepository.deleteById(id);
    }
}
