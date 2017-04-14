package com.portal.model;

import com.portal.email.EmailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scheduled_emails")
public class ScheduledEmail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/*@Column(name = "additionalData")
	private String additional_Data;*/

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "sent")
	private boolean sent;

	@Column(name = "type")
	private EmailType type;

	@Column(name = "sentDate")
	private LocalDateTime sentDate;

	@Column(name = "additional_data")
	private String additionalData;

}
