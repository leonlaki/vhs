package vhslab.VHS.service;

import org.springframework.stereotype.Service;
import vhslab.VHS.model.VHS;
import vhslab.VHS.repository.VHSRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VHSService {

    private final VHSRepository vhsRepository;

    public VHSService(VHSRepository vhsRepository) {
        this.vhsRepository = vhsRepository;
    }

    public VHS create(VHS vhs) {
        return vhsRepository.save(vhs);
    }

    public Optional<VHS> findById(Long vhsId) {
        return vhsRepository.findById(vhsId);
    }

    public List<VHS> findAll() {
        return vhsRepository.findAll();
    }

    public void delete(Long vhsId) {
        vhsRepository.deleteById(vhsId);
    }

}
