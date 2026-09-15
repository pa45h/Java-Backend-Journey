import React, { useEffect, useState } from "react";
import Card from "./Card";
import { useNavigate } from "react-router";

const FindJobs = () => {
  const [jobs, setJobs] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();

  async function fetchJobs() {
    try {
      const response = await fetch("http://localhost:8080/posts");
      const data = await response.json();
      setJobs(data);
    } catch (error) {
      console.log("Error fetching jobs -- ", error);
    }
  }

  async function fetchSearchedJobs(searchTerm) {
    try {
      const response = await fetch(`http://localhost:8080/posts/${searchTerm}`);
      const data = await response.json();
      setJobs(data);
    } catch (error) {
      console.log("Error fetching searched jobs -- ", error);
    }
  }

  useEffect(() => {
    fetchJobs();
    console.log("jobs -- ", jobs);
  }, []);

  return (
    <div className="relative min-h-screen flex items-center justify-center flex-col">
      <div className="absolute top-0 mt-6 flex space-x-4 justify-between w-full">
        <button
          onClick={() => navigate("/add-jobs")}
          className="bg-blue-500 text-white rounded-md px-4 py-2 mb-4"
        >
          Add Jobs
        </button>
        <button
          onClick={() => navigate("/")}
          className="bg-blue-500 text-white rounded-md px-4 py-2 mb-4"
        >
          Home
        </button>
      </div>
      <h1 className="text-3xl font-bold text-center mt-10">Find Jobs</h1>
      <p className="text-lg mt-4">
        Here you can find job listings and opportunities.
      </p>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          fetchSearchedJobs(searchTerm);
        }}
      >
        <input
          type="text"
          placeholder="Search for jobs..."
          className="border border-gray-300 rounded-md px-4 py-2 mt-4 w-full max-w-md"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button
          type="submit"
          className="bg-blue-500 text-white rounded-md px-4 py-2 mt-4"
        >
          Search
        </button>
      </form>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-6 w-full max-w-5xl">
        {jobs.map((job) => (
          <Card key={job.id} {...job} />
        ))}
      </div>
    </div>
  );
};

export default FindJobs;
