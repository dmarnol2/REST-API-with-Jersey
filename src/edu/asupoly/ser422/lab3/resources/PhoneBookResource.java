package edu.asupoly.ser422.lab3.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.model.PhoneEntry;
import edu.asupoly.ser422.lab3.services.PhoneService;
import edu.asupoly.ser422.lab3.services.PhoneServiceImpl;

/**
 * @apiDefine BadRequestError
 * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
 * 
 * */
/**
 * @apiDefine ActivityNotFoundError
 * @apiError (Error 4xx) {404} NotFound Activity cannot be found
 * 
 * */
/**
 * @apiDefine BadMethodError
 * @apiError (Error 4xx) {405} BadMethod Method not allowed
 * 
 * */
/**
 * @apiDefine InternalServerError
 * @apiError (Error 5xx) {500} InternalServerError Error with server
 * */
/**
 * @apiDefine NotImplementedError
 * @apiError (Error 5xx) {501} NotImplemented Resource not yet implemented
 * */

@Path("/phoneBook")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class PhoneBookResource {

	private static PhoneService __pService = PhoneServiceImpl.getInstance();
	@Context
	private UriInfo _uriInfo;
	
	/**
	 * @api {get} /phoneBook/{bookName} Get entries in a specific phone book
	 * @apiName listEntries
	 * @apiGroup BookEntries
	 * 
	 * @apiParam {String} bookName Mandatory name of phone book.
	 * 
	 * @apiDescription Returns details associated with each entry in a specific
	 * phone book. Provides user first and last name, name of phone book, 
	 * and the user id.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		{json} HTTP/1.1 200 OK
	 * 	
	 * 		[{"firstname":"dave","lastname":"matthew","phone":"3172944521","book":" ","id":0},
	 * 		 {"firstname":"mike","lastname":"jones","phone":"8125551212","book":" ","id":1}]
	 * 
	 * */
	//Use Path Param because it is specific location to a resource
	@GET
	@Path("/{bookName}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response listEntries(@PathParam("bookName") String bookName) {

		List<PhoneEntry> result = __pService.listEntries(bookName);

		try {
			String s = new ObjectMapper().writeValueAsString(result);
			return Response.status(200).entity(s).build();
		} catch (Exception exc) {
			return Response.status(200).entity("No entries exist for that input").build();
		}
	}
	
	/**
	 * @api {get} phoneBook/searchBook  Search entries in a book based on name
	 * @apiName getSet
	 * @apiGroup BookEntries
	 * 
	 * @apiParam {String} bookName  	Mandatory name of phone book.
	 * @apiParam {String} firstname  	Mandatory Firstname.
	 * @apiParam {String} lastname     	Mandatory Lastname.
	 * 
	 * @apiDescription Returns details associated in a specific phone book based on name. 
	 * Provides user first and last name, name of phone book, and the user id.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: {json} 
	 * 		HTTP/1.1 200 OK
	 * 		[{"firstname":"dave","lastname":"matthew","phone":"3172944521","book":" ","id":0}]
	 * 
	 * */
	@GET
	@Path("/searchBook")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getSet(@QueryParam("bookName") String bookName, @QueryParam("firstname") String firstname,
			@QueryParam("lastname") String lastname) {

		String[] aString = __pService.getSet(bookName, firstname, lastname);
		//if(aString[0].equals("[]")) {
		 //return Response.status(200).entity("No entries exist for that
		// input").build();
		// }
		return Response.status(200).entity(aString).build();

	}
	
	/**
	 * @api {post} phoneBook/addBook Adds a phone number to a book.
	 * @apiName addToBook
	 * @apiGroup BookEntries
	 * 
	 * @apiParam {String} bookName  	Mandatory name of phone book.
	 * @apiParam {String} userId	 	Mandatory Id associated with phone entry.
	 * 
	 * @apiDescription Adds phone entry to a specific phone book. Verifies first that phone
	 * number is not already assigned to a book.
	 * 
	 * 
	 * @apiUse BadRequestError
	 * @apiUse ActivityNotFoundError
	 * @apiUse BadMethodError
	 * @apiUse InternalServerError
	 * @apiUse NotImplementedError
	 * 
	 * @apiSuccessExample Success-Response: 
	 * 		HTTP/1.1 201 Created
	 * 
	 * 		Location → http://localhost:8080/lab3_dmarnol2/rest/phoneBook/BookName/0
	 *  
	 * */
	@POST
	@Path("/addBook")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addToBook(@FormParam("bookName") String bookName, @FormParam("userId") String userId) {
		int result = __pService.addToBook(bookName, userId);

		if (result == -1) {
			return Response.status(500).entity("{ \" EXCEPTION CREATING ENTRY \"}").build();
		} else if (result == 0) {
			return Response.status(400).entity("{ \" Creation not allowed: Entry already exists in a phone book \"}")
					.build();
		} else {
			return Response.status(201)
					.header("Location", String.format("%s/%s", _uriInfo.getAbsolutePath().toString(), bookName))
					.entity("{ \"entryId\" : \"" + bookName + "\"}").build();
		}

	}
}
