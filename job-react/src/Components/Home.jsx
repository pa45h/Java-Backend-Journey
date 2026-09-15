import React from "react";
import { useNavigate } from "react-router";

function Home() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-[#242424] text-white flex flex-col items-center justify-center px-6">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold mb-4 bg-linear-to-r from-indigo-500 to-purple-500 bg-clip-text text-transparent">
          Job Portal
        </h1>
        <p className="text-gray-400 text-lg max-w-xl">
          Discover opportunities, connect with professionals, and build your
          career with the right technology stack.
        </p>
      </div>

      <div className="flex gap-6">
        <button
          onClick={() => navigate("/find-jobs")}
          className="px-6 py-3 bg-indigo-600 hover:bg-indigo-700 rounded-xl font-semibold shadow-lg hover:scale-105 transition-all duration-300"
        >
          Find Jobs
        </button>

        <button
          onClick={() => navigate("/add-jobs")}
          className="px-6 py-3 bg-gray-800 border border-gray-700 hover:bg-gray-700 rounded-xl font-semibold shadow-lg hover:scale-105 transition-all duration-300"
        >
          Add Jobs
        </button>
      </div>
    </div>
  );
}

export default Home;
