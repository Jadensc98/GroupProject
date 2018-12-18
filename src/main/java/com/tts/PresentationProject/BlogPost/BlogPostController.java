package com.tts.PresentationProject.BlogPost;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tts.PresentationProject.BlogPost.BlogPostRepository;
import com.tts.PresentationProject.BlogPost.User;
import com.tts.PresentationProject.BlogPost.UserRepository;
import com.tts.PresentationProject.BlogPost.BlogPost;

@Controller
@RequestMapping
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private UserRepository UserRepository;
	
	//introduction page 
		@GetMapping("/")
		public ModelAndView index() {
			ModelAndView mv = new ModelAndView("blogposts/index.html");
			return mv;
		}
		
		@GetMapping("posts")
		public String posts(BlogPost blogPost) {
			return "blogposts/posts";
		}
		
		@GetMapping("/create")
		public String createpost(BlogPost blogPost) {
			return "blogposts/create_post";
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
		
		@GetMapping("/create_account")
		public String signup(User user) {
			return "blogposts/create_account";
		}
		
		@GetMapping("/Result")
		public String result(User user) {
			return "blogposts/result";
		}
		
		@RequestMapping(value = "blogposts/create_account", method = RequestMethod.POST) 
		@PostMapping(value="blogposts/create_account") 
		public String addNewUser(User user, Model model) {
		UserRepository.save(new User(user.getUserName(), user.getPassWord()));
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("passWord", user.getPassWord());
			return "blogposts/result";
			
		}
		
		@RequestMapping(value = "blogposts/create_post", method = RequestMethod.POST) 
		@PostMapping(value="blogposts/create_post") 
		public String addNewPost(BlogPost blogPost, Model model) {
		BlogPostRepository.save(new BlogPost(blogPost.getName(), blogPost.getWhere(), blogPost.getStory()));
		model.addAttribute("Name", blogPost.getName());
		model.addAttribute("Where", blogPost.getWhere());
		model.addAttribute("Story", blogPost.getStory());
			return "/";
			
		}
		
		
		
		
}
