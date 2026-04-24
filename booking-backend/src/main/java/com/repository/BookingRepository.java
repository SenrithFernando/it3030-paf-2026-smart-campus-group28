package com.smartcampus.repository;

import com.smartcampus.model.Booking;
import com.smartcampus.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByUserId(Long userId);
    
    List<Booking> findByResourceId(Long resourceId);
    
    List<Booking> findByStatus(BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.resource.id = :resourceId " +
           "AND b.status = 'CONFIRMED' " +
           "AND ((b.startTime BETWEEN :startTime AND :endTime) " +
           "OR (b.endTime BETWEEN :startTime AND :endTime) " +
           "OR (:startTime BETWEEN b.startTime AND b.endTime))")
    List<Booking> findConflictingBookings(@Param("resourceId") Long resourceId,
                                          @Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);
}