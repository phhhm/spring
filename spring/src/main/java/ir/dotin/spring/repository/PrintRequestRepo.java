package ir.dotin.spring.repository;

import ir.dotin.spring.model.PrintRequest;
import ir.dotin.spring.model.PrintRequestEmbeddedId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrintRequestRepo extends PagingAndSortingRepository<PrintRequest, PrintRequestEmbeddedId> {


    Page<PrintRequest> findAll(Pageable pageable);

    Optional<PrintRequest> findByCode(Long Code);

    boolean existsPrintRequestByBranchCodeAndIpAddress(String branchCode, String ipAddress);

    boolean existsByCode(String code);

    @Query("select pr.cardPAN from PrintRequest  pr where pr.branchCode =:branchCode ")
    Page<PrintRequest> getCardPANByBranchCode(@Param("branchCode") String branchCode, Pageable pageable);

    @Modifying
    @Query(value = "update t_PrintRequest set c_cardPAN = :cardPAN where c_code = :code ", nativeQuery = true)
    void updateCardPanByBranchCode(@Param("code") String branchCode, @Param("cardPAN") String cardPAN);

}
