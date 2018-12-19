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
	//shows the form for creating a blog post
	@GetMapping("/newpost")
	public String newPostForm(BlogPost blogPost) {
		return "blogposts/create_post.html";
				
	}
	@PostMapping("/newpost")
	 public String createPost(BlogPost blogPost,Model model ){
				BlogPost post = blogPostRepository.save(new BlogPost(blogPost.getName(),blogPost.getPlace(),blogPost.getStory(),blogPost.getUpdateDttm()));
				model.addAttribute("name",post.getName());
				model.addAttribute("place",post.getPlace());
				model.addAttribute("story",post.getStory());
				model.addAttribute("updateDttm",post.getUpdateDttm());
						return "blogposts/newpost.html";
				
			}		
	@GetMapping("/comments")
	public String new_comment(Comments comments) {
			return "blogpots/create_comment.html";
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
	//Shows the list of posts
	@GetMapping("/postlists")
	public String post_list(Model model) {
		model.addAttribute("blogposts",blogPostRepository.findAll());
		return "blogposts/postlist.html";
	}
	@GetMapping("/contactus")
	public String contact() {
		
		return "blogposts/contactus.html";
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
