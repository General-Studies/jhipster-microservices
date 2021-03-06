package com.jetherrodrigues.blog.web.rest;

import com.jetherrodrigues.blog.domain.Post;
import com.jetherrodrigues.blog.repository.PostRepository;
import com.jetherrodrigues.blog.repository.search.PostSearchRepository;
import com.jetherrodrigues.blog.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.jetherrodrigues.blog.domain.Post}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    private static final String ENTITY_NAME = "blogPost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostRepository postRepository;

    private final PostSearchRepository postSearchRepository;

    public PostResource(PostRepository postRepository, PostSearchRepository postSearchRepository) {
        this.postRepository = postRepository;
        this.postSearchRepository = postSearchRepository;
    }

    /**
     * {@code POST  /posts} : Create a new post.
     *
     * @param post the post to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new post, or with status {@code 400 (Bad Request)} if the post has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws URISyntaxException {
        log.debug("REST request to save Post : {}", post);
        if (post.getId() != null) {
            throw new BadRequestAlertException("A new post cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Post result = postRepository.save(post);
        postSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /posts} : Updates an existing post.
     *
     * @param post the post to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated post,
     * or with status {@code 400 (Bad Request)} if the post is not valid,
     * or with status {@code 500 (Internal Server Error)} if the post couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/posts")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) throws URISyntaxException {
        log.debug("REST request to update Post : {}", post);
        if (post.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Post result = postRepository.save(post);
        postSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, post.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /posts} : get all the posts.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posts in body.
     */
    @GetMapping("/posts")
    public List<Post> getAllPosts(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Posts");
        return postRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /posts/:id} : get the "id" post.
     *
     * @param id the id of the post to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the post, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        Optional<Post> post = postRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(post);
    }

    /**
     * {@code DELETE  /posts/:id} : delete the "id" post.
     *
     * @param id the id of the post to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        postRepository.deleteById(id);
        postSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/posts?query=:query} : search for the post corresponding
     * to the query.
     *
     * @param query the query of the post search.
     * @return the result of the search.
     */
    @GetMapping("/_search/posts")
    public List<Post> searchPosts(@RequestParam String query) {
        log.debug("REST request to search Posts for query {}", query);
        return StreamSupport
            .stream(postSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
