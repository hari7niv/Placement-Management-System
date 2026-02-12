    package com.example.PMS.Entity;

    import java.time.LocalDateTime;

    import org.hibernate.annotations.UpdateTimestamp;

    import com.example.PMS.DTO.ApplicationStatusDTO;
    import com.example.PMS.Enum.Status;

    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import lombok.Data;

    @Entity
    @Data
    public class ApplicationStatus {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long status_id;

        @ManyToOne
        @JoinColumn(name = "application_id")
        private Applications application;

        @Enumerated(EnumType.STRING)
        private Status status;

        @UpdateTimestamp
        private LocalDateTime updated_at;

        private String remarks;

        public ApplicationStatusDTO toDTO() {
            ApplicationStatusDTO asd = new ApplicationStatusDTO();
            asd.setAid(status_id);
            asd.setRemarks(remarks);
            asd.setStatus(status);
            return asd;
        }
    }

    // create a dto with remarks applicationid and status and then return itto the
    // get function after comming form restroom