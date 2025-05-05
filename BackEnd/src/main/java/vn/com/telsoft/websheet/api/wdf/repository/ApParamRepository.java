//package vn.com.telsoft.websheet.api.wdf.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import vn.com.telsoft.websheet.api.wdf.model.ApParam;
//
//import java.util.List;
//
///**
// * Project: WDF
// * Author: LAMLT
// * Created on: 28/2/25
// */
//
//public interface ApParamRepository extends JpaRepository<ApParam, Long> {
//    List<ApParam> findAllByTypeOrderByIdDesc(String type);
//    ApParam findByValue(String value);
//}
