package com.tts.PresentationProject.BlogPost;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private CommentRepository commentRepository;
	
	//introduction page
	@GetMapping("/newuser")
	public ModelAndView create_user(User user) {
		ModelAndView mv = new ModelAndView("blogposts/create_account");
		return mv;
	}
	@PostMapping("/newuser")
	public String create_account(User user, Model model) {
		User user1 = userRepository.save(new User(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getUserName(),new Date()));
		model.addAttribute("firstName", user1.getFirstName());
		model.addAttribute("lastName", user1.getLastName());
		model.addAttribute("email", user1.getEmail());
		model.addAttribute("userName", user1.getUserName());
		model.addAttribute("updateDttm", user1.getUpdateDttm());
		System.out.println("---> User Name: "+ user1.getFirstName());
		return "blogposts/view_account";
//		ModelAndView mv = new ModelAndView("blogposts/view_account");
//		mv.addObject("user",user1);
//		return mv;
		
	}
	@GetMapping("/comments")
	public ModelAndView new_comment(Comments comments) {
		ModelAndView mv = new ModelAndView("blogposts/create_comment");
		return mv;
	}
	@PostMapping("/comments")
	public String view_comment(Comments comments,Model model) {
		Comments comments1 = commentRepository.save(new Comments(comments.getCommentPost(), new Date()));
			//ModelAndView mv = new ModelAndView("blogposts/post_comments");
			//mv.addObject("Comments",comments1);
			//return mv;
		model.addAttribute("commentPost", comments1.getCommentPost());
		model.addAttribute("updateDttm", comments1.getUpdateDttm());
		return "blogposts/post_comments";
		
		
	}
	
	@GetMapping("/")
		public ModelAndView index(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/index");
			return mv;
		}
	@GetMapping("/allposts")
	public ModelAndView all_posts(BlogPost blogpost) {
		ModelAndView mv = new ModelAndView("blogposts/posts.html");
		return mv;
	}
		
		
		
		
		
		//handles the saving of the new blog post
	
		@PostMapping("/blog_posts/new")
		public ModelAndView createPost(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/result");
			BlogPost post = blogPostRepository.save(new BlogPost(blogPost.getName(), blogPost.getStory(), blogPost.getPlace(), new Date()));
			mv.addObject("post", post);
			return mv;
		}
		
		//shows the form for creating a blog post
		@GetMapping("/newpost")
		public ModelAndView newPostForm(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/create_post");
			return mv;
		}
		
		
		//shows the form for editing a blog post
		@GetMapping("/blog_posts/edit/{id}")
		public ModelAndView updatePostForm(@PathVariable("id") long id) {
			ModelAndView mv = new ModelAndView("blogposts/edit_post");
			Optional<BlogPost> post = blogPostRepository.findById(id);
			mv.addObject("blogPost", post);
			return mv;
		}
		
		//saves the edits to the blog post
		@PutMapping("/blog_posts/edit")
		public ModelAndView updatePost(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("redirect:/");
			blogPostRepository.save(blogPost);
			return mv;
		}
		
		//shows the form for editing a blog post
		@DeleteMapping("/blog_posts/delete/{id}")
		public ModelAndView deletepost(@PathVariable("id") long id) {
			ModelAndView mv = new ModelAndView("redirect:/");
			blogPostRepository.deleteById(id);
			return mv;
		}
		
		
}
