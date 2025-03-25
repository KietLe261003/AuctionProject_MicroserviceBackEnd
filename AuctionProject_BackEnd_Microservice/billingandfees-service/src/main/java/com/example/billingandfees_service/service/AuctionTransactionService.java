package com.example.billingandfees_service.service;

import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.dto.request.AuctionTransactionRequest;
import com.example.billingandfees_service.dto.request.AuctionTransactionUpdate;
import com.example.billingandfees_service.entity.AuctionTransaction;
import com.example.billingandfees_service.mapper.AuctionTransactionMapper;
import com.example.billingandfees_service.repository.AuctionTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionTransactionService {
    @Autowired
    private AuctionTransactionRepository auctionTransactionRepository;
    @Autowired
    AuctionTransactionMapper auctionTransactionMapper;

    public List<AuctionTransaction> findAll() {
        return auctionTransactionRepository.findAll();
    }
    public AuctionTransaction findById(Long id) {
        return auctionTransactionRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND_Transaction));
    }
    public AuctionTransaction save(AuctionTransactionRequest auctionTransactionRequest) {
        AuctionTransaction check = auctionTransactionRepository.findByAuctionIdAndUserId(auctionTransactionRequest.getAuctionId(), auctionTransactionRequest.getUserId());
        if (check != null) {
            return check;
        }
        AuctionTransaction auctionTransaction = auctionTransactionMapper.toAuctionTransaction(auctionTransactionRequest);
        auctionTransaction.setStatus("Pending");
        auctionTransaction.setCreateAt(LocalDateTime.now());
        auctionTransaction.setUpdateAt(LocalDateTime.now());
        return auctionTransactionRepository.save(auctionTransaction);
    }
    public AuctionTransaction update(Long id,AuctionTransactionUpdate auctionTransactionUpdate) {
        AuctionTransaction auctionTransaction = findById(id);
        auctionTransactionMapper.updateAuctionTransaction(auctionTransaction, auctionTransactionUpdate);
        if(auctionTransaction.getStatus().equals("Complete"))
        {
            auctionTransaction.setSubmitDate(LocalDateTime.now());
        }
        auctionTransaction.setUpdateAt(LocalDateTime.now());
        return auctionTransactionRepository.save(auctionTransaction);
    }
    public void delete(Long id) {
        auctionTransactionRepository.deleteById(id);
    }
    public void checkAndUpdateExpiredTransactions() {
        List<AuctionTransaction> pendingTransactions = auctionTransactionRepository
                .findByStatus("Pending");

        LocalDateTime now = LocalDateTime.now();
        for (AuctionTransaction transaction : pendingTransactions) {
            if (transaction.getDeadlineDate() != null && transaction.getDeadlineDate().isBefore(now)) {
                // Nếu deadlineDate đã quá thời gian hiện tại, cập nhật trạng thái thành "Fail"
                transaction.setStatus("Fail");
                transaction.setUpdateAt(now);
                auctionTransactionRepository.save(transaction);
            }
        }
    }
    public List<AuctionTransaction> findByUserId(Long userId) {
        return auctionTransactionRepository.findByUserId(userId);
    }
    @Scheduled(fixedRate = 3600000) // Mỗi giờ kiểm tra
    public void PaymentDeadline() {
        checkAndUpdateExpiredTransactions();
    }

    public Boolean checkTransactionByAuctionIdAndUserId(Long auctionId, Long userId) {
        return auctionTransactionRepository.existsAllByAuctionIdAndUserId(auctionId, userId);
    }
}
