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
import org.springframework.web.servlet.ModelAndView;

import com.tts.PresentationProject.BlogPost.BlogPostRepository;
import com.tts.PresentationProject.BlogPost.User;
import com.tts.PresentationProject.BlogPost.UserRepository;
import com.tts.PresentationProject.BlogPost.BlogPost;

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
		
		@GetMapping("/blogpost/new")
	    public ModelAndView newPostForm(BlogPost blogPost) {
	        ModelAndView mv = new ModelAndView("blogpost/create_post");
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
		
		//shows the form for creating a blog post
		@GetMapping("/newpost")
		public String newPost(BlogPost blogPost) {
			return "blogposts/create_post.html";
					
		}
		@PostMapping("/newpost")
		 public String createPost(BlogPost blogPost, Model model ){
					BlogPost post = blogPostRepository.save(new BlogPost(blogPost.getName(),blogPost.getPlace(),blogPost.getStory(),blogPost.getUpdateDttm()));
					model.addAttribute("name",post.getName());
					model.addAttribute("place",post.getWhere());
					model.addAttribute("story",post.getStory());
					model.addAttribute("updateDttm",post.getUpdateDttm());
							return "blogposts/newpost.html";
					
				}
		
		@GetMapping("/create_account")
		public String signup(User user) {
			return "blogposts/create_account";
		}
		
		@GetMapping("/Result")
		public String result(User user) {
			return "blogposts/result";
		}
		
		@PostMapping("/blogpost/newuser")
	    public ModelAndView createUser(User user) {
			ModelAndView mv = new ModelAndView("blogpost/userResult");
			User account = userRepository.save(user);
	        mv.addObject("user", account);
	        return mv;
	    }
		
		@PostMapping("/blogpost/new")
	    public ModelAndView createPost(BlogPost blogPost) {
			ModelAndView mv = new ModelAndView("blogpost/result");
			BlogPost post = blogPostRepository.save(blogPost);
	        mv.addObject("post", post);
	        return mv;
	    }
		
		
		
		
}
