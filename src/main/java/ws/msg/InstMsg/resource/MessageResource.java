package ws.msg.InstMsg.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	MessageService service = new MessageService();
	
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		List<Message> l = service.getAllMessages("pass");
		return l;
	}
	
	@GET
	@Path("/{messageId}")
	public Message get(@PathParam("messageId") int id) {
		return service.getMessage(id, "pass");
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message delete(@PathParam("messageId") int id) {
		return service.deleteMessage(id, "pass");
	}
	
	@POST
	public Message create(Message msg) {
		return service.createMessage(msg, "pass");
	}
	
	@PUT
	@Path("/{messageId}")
	public Message update(Message msg, 
			@PathParam("messageId") int id) {
		return service.updateMessage(id, msg, "pass");
	}
	
}
