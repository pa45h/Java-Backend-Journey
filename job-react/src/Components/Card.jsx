import React from "react";

const Card = ({ profile, desc, exp, techs }) => {
  return (
    <div className="bg-mist-900 text-gray-100 shadow-lg rounded-2xl p-6 mb-6 border border-gray-800 hover:shadow-2xl hover:scale-[1.02] transition-all duration-300">
      <div className="flex justify-between items-center mb-3">
        <h2 className="text-xl font-semibold capitalize text-white">
          {profile}
        </h2>
        <span className="bg-indigo-600 text-white text-sm px-3 py-1 rounded-full">
          {exp} yrs
        </span>
      </div>

      <p className="text-gray-400 mb-4">{desc}</p>

      <div className="flex flex-wrap gap-2 justify-center">
        {techs?.map((tech, index) => (
          <span
            key={index}
            className="bg-gray-800 text-gray-300 text-xs px-3 py-1 rounded-full border border-gray-700 hover:bg-indigo-600 hover:text-white transition-colors duration-200"
          >
            {tech}
          </span>
        ))}
      </div>
    </div>
  );
};

export default Card;
