//package vn.com.telsoft.websheet.api.wdf.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO;
//import vn.com.telsoft.websheet.api.wdf.model.CarrierMappings;
//
//import java.util.List;
//
///**
// * Project: WDF
// * Author: LAMLT
// * Created on: 28/2/25
// */
//
//public interface CarrierMappingsRepository extends JpaRepository<CarrierMappings, Long> {
//    @Query(value = "select new vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO(cm.id,cm.carrierId,cm.smartWatchId,cm.logoId,cr.logoId,cm.status,cm.style,cm.description,lg.data,sw.name)\n" +
//            "from CarrierMappings cm\n" +
//            "         join SmartWatch sw on cm.smartWatchId = sw.id\n" +
//            "         join Logo lg on sw.logoId = lg.id" +
//            "         join Carrier cr on cr.id = cm.carrierId" +
//            " where cm.carrierId = :carrierId" +
//            " order by cm.id desc ")
//    List<CarrierMappingsDTO> findAllByCarrierId(Long carrierId);
//
//    @Query(value = "select new vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO(cm.id,cm.carrierId,cm.smartWatchId,cm.logoId,cr.logoId,cm.status,cm.style,cm.description,lg.data,sw.name)\n" +
//            "from CarrierMappings cm\n" +
//            "         join SmartWatch sw on cm.smartWatchId = sw.id\n" +
//            "         join Logo lg on sw.logoId = lg.id\n" +
//            "         join Carrier cr on cr.id = cm.carrierId\n" +
//            "         where cm.id=:id")
//    CarrierMappingsDTO findByCarrierId(Long id);
//}
