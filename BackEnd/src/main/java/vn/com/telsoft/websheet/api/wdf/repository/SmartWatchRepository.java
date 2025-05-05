//package vn.com.telsoft.websheet.api.wdf.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
//import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
//import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;
//
//import java.util.List;
//
///**
// * Project: WDF
// * Author: LAMLT
// * Created on: 28/2/25
// */
//
//public interface SmartWatchRepository extends JpaRepository<SmartWatch, Long> {
//    @Query("SELECT new vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO(" +
//            "cr.id, cr.name, cr.logoId, lg.data, cr.status,FUNCTION('TO_CHAR', cr.createdDate, 'Mon DD, YYYY')) " +
//            "FROM SmartWatch cr LEFT JOIN Logo lg ON lg.id = cr.logoId ORDER BY cr.id desc ")
//    List<SmartWatchDTO> findAllSmartWatches();
//}
