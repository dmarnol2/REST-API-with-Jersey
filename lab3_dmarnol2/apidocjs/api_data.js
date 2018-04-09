define({ "api": [
  {
    "type": "post",
    "url": "phoneBook/addBook",
    "title": "Adds a phone number to a book.",
    "name": "addToBook",
    "group": "BookEntries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bookName",
            "description": "<p>Mandatory name of phone book.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>Mandatory Id associated with phone entry.</p>"
          }
        ]
      }
    },
    "description": "<p>Adds phone entry to a specific phone book. Verifies first that phone number is not already assigned to a book.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 201 Created",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneBookResource.java",
    "groupTitle": "BookEntries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "phoneBook/searchBook",
    "title": "Search entries in a book based on name",
    "name": "getSet",
    "group": "BookEntries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bookName",
            "description": "<p>Mandatory name of phone book.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "firstname",
            "description": "<p>Mandatory Firstname.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lastname",
            "description": "<p>Mandatory Lastname.</p>"
          }
        ]
      }
    },
    "description": "<p>Returns details associated in a specific phone book based on name. Provides user first and last name, name of phone book, and the user id.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: {json} ",
          "content": "HTTP/1.1 200 OK\n[{\"firstname\":\"dave\",\"lastname\":\"matthew\",\"phone\":\"3172944521\",\"book\":\" \",\"id\":0}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneBookResource.java",
    "groupTitle": "BookEntries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/phoneBook/{bookName}",
    "title": "Get entries in a specific phone book",
    "name": "listEntries",
    "group": "BookEntries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bookName",
            "description": "<p>Mandatory name of phone book.</p>"
          }
        ]
      }
    },
    "description": "<p>Returns details associated with each entry in a specific phone book. Provides user first and last name, name of phone book, and the user id.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "{json} HTTP/1.1 200 OK\n\n[{\"firstname\":\"dave\",\"lastname\":\"matthew\",\"phone\":\"3172944521\",\"book\":\" \",\"id\":0},\n {\"firstname\":\"mike\",\"lastname\":\"jones\",\"phone\":\"8125551212\",\"book\":\" \",\"id\":1}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneBookResource.java",
    "groupTitle": "BookEntries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/phoneEntry/newEntry",
    "title": "Create phone entry.",
    "name": "createEntry",
    "group": "Entries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "fname",
            "description": "<p>Mandatory Firstname.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lname",
            "description": "<p>Mandatory Lastname.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phoneNum",
            "description": "<p>Mandatory Phone number of the User.</p>"
          }
        ]
      }
    },
    "description": "<p>Takes input of first name, last name, and phone number and creates an unlisted entry. UserID is assigned by the program.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "{json} HTTP/1.1 201 Created\n\n{ \"entryId\" : \"0\"}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "Entries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "delete",
    "url": "/phoneEntry/{id}",
    "title": "Delete phone entry.",
    "name": "deleteEntry",
    "group": "Entries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Mandatory The ID assigned to user at creation.</p>"
          }
        ]
      }
    },
    "description": "<p>Deletes phone entry from either unlisted file or phone book.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 204 No Content",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "Entries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/phoneEntry/{phSearch}",
    "title": "Get entry based on phone number",
    "name": "getPhoneEntry",
    "group": "Entries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phoneNum",
            "description": "<p>Mandatory phone number.</p>"
          }
        ]
      }
    },
    "description": "<p>Returns details associated with phone number. Searches unlisted and phone books. Provides user first and last name, name of phone book if it belongs to one, and the user id.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "{json} HTTP/1.1 200 OK\n[{\"firstname\":\"dave\",\"lastname\":\"matthew\",\"phone\":\"3172944521\",\"book\":\" \",\"id\":0}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "Entries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/phoneEntry/unlisted",
    "title": "Get list of all unlisted phone entries.",
    "name": "getUnlisted",
    "group": "Entries",
    "description": "<p>This method returns all the numbers in the system that has not been added to a phone book. A phone number can only be added once so it is either in a phone book or unlisted.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "{json} HTTP/1.1 200 OK\n\n[{\"firstname\":\"dave\",\"lastname\":\"matthew\",\"phone\":\"3172944521\",\"book\":\" \",\"id\":0},\n {\"firstname\":\"mike\",\"lastname\":\"jones\",\"phone\":\"8125551212\",\"book\":\" \",\"id\":1}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "Entries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  },
  {
    "type": "put",
    "url": "/phoneEntry",
    "title": "Change first name and last name.",
    "name": "updateEntry",
    "group": "Entries",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>Mandatory User identifcation number.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "fname",
            "description": "<p>Mandatory Firstname of the User.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lname",
            "description": "<p>Mandatory Lastname of the User.</p>"
          }
        ]
      }
    },
    "description": "<p>Changes the first and last name of user associated with phone entry.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response: ",
          "content": "HTTP/1.1 204 No Content",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "Entries",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          },
          {
            "group": "Error 4xx",
            "type": "405",
            "optional": false,
            "field": "BadMethod",
            "description": "<p>Method not allowed</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Error with server</p>"
          },
          {
            "group": "Error 5xx",
            "type": "501",
            "optional": false,
            "field": "NotImplemented",
            "description": "<p>Resource not yet implemented</p>"
          }
        ]
      }
    }
  }
] });
