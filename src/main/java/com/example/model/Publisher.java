package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherId;
    
    @NotBlank(message = "Publisher name is required")
    @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
    private String publisherName;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;
    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    private String contactPhone;
    
}
