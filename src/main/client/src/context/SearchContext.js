import React, { createContext, useState } from "react";

export const SearchContext = createContext();

const SearchProvider = ({ children }) => {
    const [search, setSearch] = useState(null)
    const onSearch = search => {
        setSearch(search);
    };
    return (
        <SearchContext.Provider value = {{ search, onSearch }}>
            { children }
        </SearchContext.Provider>
    )
}

export default SearchProvider;