package com.example.PMS.Service;


import com.example.PMS.DTO.AdminUpdateStudents;
import com.example.PMS.Entity.Admin;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.AdminRepository;
import com.example.PMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  java.util.List;
@Service
public class AdminService {
    final AdminRepository repo;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    public Admin createAdmin(Admin admin){
        return repo.save(admin);
    }

    public String Adminlog(String email, String password) {

        Admin admin = repo.findByEmail(email).orElseThrow(()->new RuntimeException("Admin not found "));
        if (!password.equals(admin.getPassword_hash())) {
            throw new RuntimeException("Invalid email or password");
        }
        return "Login Successful";
    }

    public List<Admin> viewAdmins(){
        return repo.findAll();
    }


    @Autowired
    private StudentRepository studentRepository;

    public String verifyStudent(Long id){
        Students students = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Student Id"));
        students.setIs_verified(true);
        return "student "+students.getFirst_name()+" verified";
    }
    public String rejectStudent(Long id){
        Students students = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Student Id"));
        students.setIs_verified(false);
        return "student "+students.getFirst_name()+" Rejected";
    }

    public String updateStudent(AdminUpdateStudents data,Long id){
        Students students = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Student Id"));
        students.setBranch(data.getBranch());
        students.setCgpa(data.getCgpa());
        students.setBacklog_count(data.getBacklog_count());
        return "student "+students.getFirst_name()+" Updated";
    }



}
