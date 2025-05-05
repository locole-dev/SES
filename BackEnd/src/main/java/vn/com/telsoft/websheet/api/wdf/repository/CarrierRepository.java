//package vn.com.telsoft.websheet.api.wdf.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
//import vn.com.telsoft.websheet.api.wdf.model.Carrier;
//
//import java.util.List;
//
///**
// * Project: WDF
// * Author: LAMLT
// * Created on: 28/2/25
// */
//
//public interface CarrierRepository extends JpaRepository<Carrier, Long> {
//    @Query("SELECT new vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO(" +
//            "cr.id, cr.name, cr.logoId, lg.data, cr.status,FUNCTION('TO_CHAR', cr.createdDate, 'Mon DD, YYYY')) " +
//            "FROM Carrier cr LEFT JOIN Logo lg ON lg.id = cr.logoId")
//    List<CarrierDTO> getAllCarriers();
//
//}
