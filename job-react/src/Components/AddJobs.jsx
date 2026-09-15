import React, { useState } from "react";
import { useNavigate } from "react-router";

const AddJobs = () => {
  const [post, setPost] = useState({
    profile: "",
    desc: "",
    exp: 0,
    techs: [],
  });

  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/add-post", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(post),
      });
      if (response.ok) {
        alert("Job added successfully!");
        setPost({
          profile: "",
          desc: "",
          exp: 0,
          techs: [],
        });
      } else {
        alert("Failed to add job. Please try again.");
      }
    } catch (error) {
      console.error("Error adding job:", error);
      alert("An error occurred while adding the job. Please try again.");
    }
  }

  return (
    <div className="relative min-h-screen flex items-center justify-center flex-col">
      <div className="absolute top-0 mt-6 flex space-x-4 justify-between w-full">
        <button
          onClick={() => navigate("/find-jobs")}
          className="bg-blue-500 text-white rounded-md px-4 py-2 mb-4"
        >
          Find Jobs
        </button>
        <button
          onClick={() => navigate("/")}
          className="bg-blue-500 text-white rounded-md px-4 py-2 mb-4"
        >
          Home
        </button>
      </div>
      <h1 className="text-3xl font-bold text-center mt-10">Add Jobs</h1>
      <p className="text-lg mt-4">
        Here you can add new job listings and opportunities.
      </p>
      <form onSubmit={handleSubmit} className="w-full max-w-md mt-6">
        <input
          type="text"
          placeholder="Job Profile"
          className="border border-gray-300 rounded-md px-4 py-2 mt-4 w-full max-w-md"
          value={post.profile}
          onChange={(e) => setPost({ ...post, profile: e.target.value })}
        />
        <textarea
          placeholder="Job Description"
          className="border border-gray-300 rounded-md px-4 py-2 mt-4 w-full max-w-md"
          value={post.desc}
          onChange={(e) => setPost({ ...post, desc: e.target.value })}
        />
        <input
          type="number"
          placeholder="Experience (years)"
          className="border border-gray-300 rounded-md px-4 py-2 mt-4 w-full max-w-md"
          value={post.exp}
          onChange={(e) => setPost({ ...post, exp: parseInt(e.target.value) })}
        />
        <input
          type="text"
          placeholder="Technologies (comma separated)"
          className="border border-gray-300 rounded-md px-4 py-2 mt-4 w-full max-w-md"
          value={post.techs.join(", ")}
          onChange={(e) =>
            setPost({
              ...post,
              techs: e.target.value.split(",").map((tech) => tech.trim()),
            })
          }
        />
        <button
          type="submit"
          className="bg-green-500 text-white rounded-md px-4 py-2 mt-4"
        >
          Add Job
        </button>
      </form>
    </div>
  );
};

export default AddJobs;
