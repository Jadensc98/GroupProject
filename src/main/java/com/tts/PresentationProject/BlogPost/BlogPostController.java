package com.tts.PresentationProject.BlogPost;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tts.PresentationProject.BlogPost.BlogPostRepository;
import com.tts.PresentationProject.BlogPost.User;
import com.tts.PresentationProject.BlogPost.UserRepository;
import com.tts.PresentationProject.BlogPost.BlogPost;
@RequestMapping
@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private UserRepository userRepository;
	
		//introduction page 
		@GetMapping("/")
		public ModelAndView index(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/index.html");
			mv.addObject("blogPosts", blogPostRepository.findAll());
			return mv;
		}
		
		@GetMapping("posts")
		public ModelAndView posts(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/posts");
			mv.addObject("blogPosts", blogPostRepository.findAll());
			return mv;
		}
		
		@GetMapping("view_account")
		public String posts(User user) {
			return "blogposts/view_account";
		}
	
		
		//shows the form for editing a blog post

	     @GetMapping("/blogposts/edit_post/{id}")
	     public ModelAndView updatePostForm(@PathVariable("id") long id) {
			ModelAndView mv = new ModelAndView("blogposts/edit_post");
			Optional<BlogPost> post = blogPostRepository.findById(id);
			mv.addObject("blogPost", post);
	            return mv;
	        }
		
		//saves the edits to the blog post

		@PutMapping("/blogposts/edit_post")
		public ModelAndView updatePost(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("redirect:/");
			blogPostRepository.save(blogPost);
			return mv;
		}
		

//		shows the form for editing a blog post
		@DeleteMapping("/blogposts/delete/{id}")
		public ModelAndView deletepost(@PathVariable("id") long id) {
			ModelAndView mv = new ModelAndView("redirect:/");
			blogPostRepository.deleteById(id);
			return mv;
		}	
		
		
		//shows the form for creating a blog post
		@GetMapping("/create_post")
		public ModelAndView create_post(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/create_post");
			return mv;
		}
		
		
		@PostMapping(value= "/create_post")
		 public String createPost(BlogPost blogPost, Model model ){
					BlogPost post = blogPostRepository.save(new BlogPost(blogPost.getName(),blogPost.getPlace(),blogPost.getStory(),blogPost.getUpdateDttm()));
					model.addAttribute("name",post.getName());
					model.addAttribute("place",post.getPlace());
					model.addAttribute("story",post.getStory());
					model.addAttribute("updateDttm",post.getUpdateDttm());
					model.addAttribute("postId", post.getId());
							return "blogposts/result.html";
					
				}
		
		@GetMapping("/create_account")
		public ModelAndView signup(User user) {
			ModelAndView mv = new ModelAndView("blogposts/create_account");
			return mv;
		}
		
		@GetMapping("/result")
		public ModelAndView result(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogposts/result");
			mv.addObject("newPost", blogPost);
			return mv;
		}
		
		@GetMapping("/userResult")
		public ModelAndView userResult(User user) {
			ModelAndView mv = new ModelAndView("blogposts/userResult");
			mv.addObject("newUser", user);
			return mv;
		}
		
	
		
		@PostMapping(value="/create_account") 
		public String addNewUser(User user, Model model) {
		userRepository.save(new User(user.getUserName(), user.getPassWord()));
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("passWord", user.getPassWord());
		
			return "blogposts/userResult";
			
		}
		
		
}
