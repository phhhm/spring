package ir.dotin.spring.repository.custom;

import java.util.List;

public interface PrintRequestCustomRep {
    List<String> getAllIpAddressByBranchCode(String branchCode);
}
