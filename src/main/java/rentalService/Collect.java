package rentalService;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Collect_table")
public class Collect {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long rentalId;
    private String status;

    @PostPersist
    public void onPostPersist(){

        if (status.equals("Collected")){
            Collected collected = new Collected();
            BeanUtils.copyProperties(this, collected);
            collected.setId(this.id);
            collected.setRentalId(this.rentalId);
            collected.setStatus(this.status);
            collected.publishAfterCommit();
        }

        if (status.equals("Collected_Canceled")){
            CollectCanceled collectCanceled = new CollectCanceled();
            BeanUtils.copyProperties(this, collectCanceled);
            collectCanceled.setId(this.id);
            collectCanceled.setRentalId(this.rentalId);
            collectCanceled.setReturnStatus(this.status);
            collectCanceled.publishAfterCommit();
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
