package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * The interface Message repository.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * Find top 5 by order by id desc list.
     *
     * @return the list
     */
    List<Message> findTop5ByOrderByIdDesc();

    /**
     * Find by msg containing ignore case list.
     *
     * @param msg the msg
     * @return the list
     */
    List<Message> findByMsgContainingIgnoreCase(@NotBlank(message = "Error: Empty Name") String msg);

    /**
     * Find by author containing ignore case list.
     *
     * @param author the author
     * @return the list
     */
    List<Message> findByAuthorContainingIgnoreCase(String author);
}