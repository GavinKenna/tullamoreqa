package com.gkenna.tullamoreqa.domain.repositories;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByTitle(String title, Pageable pageable);
    Page<Question> findByUserId(Long userId, Pageable pageable);
    Page<Question> findByModifiedBy(Long modUserId, Pageable pageable);
    Page<Question> getByTagsIn(Set<Tag> tags, Pageable pageable);

    /*@Query("SELECT n FROM Question n WHERE n.tags IN "
            + "SELECT t FROM Tag t  WHERE t.name in :super")*/
    //@Query("SELECT p FROM Question p WHERE p.tags IN (SELECT t FROM Tag t WHERE t.name IN (:super))")
    Collection<Question> findByTagsIn(@Param("super") List<String> tags);

    Collection<Question> findByTagsNameContaining(String tag);
    Collection<Question> findQuestionsByTagsNameIn(Set<String> tag);
    Collection<Question> findQuestionsByTagsNameContaining(Set<String> tag);

    /**
     * This works, but much like the others above. This makes sure there's at least one tag that
     * matches. Still looking for one that makes sure it contains all tags
     * @param tag
     * @return
     */
    @Query("select distinct q from Question q inner join q.tags t where t.name in :super")
    Collection<Question> findQuestionsBasedOnTagName(@Param("super") Set<String> tag);

    @Query("select distinct q from Question q inner join q.tags t where t = (select distinct g from Tag g where g" +
            ".name in :super)")
    Collection<Question> findQuestionsBasedOnTagNameQuery(@Param("super") Set<String> tag);

    @Query("select distinct q from Question q inner join q.tags t where t is (select distinct g from Tag g where g" +
            ".name in :super)")
    Collection<Question> findQuestionsBasedOnTagNameQueryNew(@Param("super") Set<String> tag);

    //Collection<Question> findQuestionsByTagsNameContaining(Set<String> tags);

    @Query("select q from Question q join q.tags t where t.name in :gk " +
            "group by q.id having count(q.id) = :#{#gk.length + 0L}")
    Collection<Question> filterQuestionsByTag(@Param("gk") String[] gk);

    @Query("select q from Question q join q.tags t where t.name in :tags " +
            "group by q.id having count(q.id) = :tagCount")
    Collection<Question> filterQuestionsByTags(@Param("tags") Set<String> tagList, @Param("tagCount") long length);


 }