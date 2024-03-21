module Main where

import Args
  ( AddOptions (..),
    Args (..),
    GetOptions (..),
    SearchOptions (..),
    parseArgs,
  )
import qualified Data.List as L
import qualified Entry.DB as DB
import Entry.Entry
  ( Entry (..),
    FmtEntry (FmtEntry),
    matchedByAllQueries,
    matchedByQuery,
  )
import Result
import System.Environment (getArgs)
import Test.SimpleTest.Mock
import Prelude hiding (print, putStrLn, readFile)
import qualified Prelude
import Entry.DB (SnippetDB, empty)

usageMsg :: String
usageMsg =
  L.intercalate
    "\n"
    [ "snip - code snippet manager",
      "Usage: ",
      "snip add <filename> lang [description] [..tags]",
      "snip search [code:term] [desc:term] [tag:term] [lang:term]",
      "snip get <id>",
      "snip init"
    ]

-- | Handle the init command
handleInit :: TestableMonadIO m => m ()
handleInit = do
  DB.save empty  
  return ()


-- | Handle the get command
handleGet :: TestableMonadIO m => GetOptions -> m ()
handleGet opts = do
  db <- DB.load
  case db of
    (Error err) -> putStrLn "Failed to load DB"
    (Success entries) ->
        case DB.findFirst (\entry -> entryId entry == getOptId opts) entries of
            Nothing ->
              putStrLn "No entry was found"
            Just entry -> do
              putStrLn $ show (entryId entry)
              putStrLn $ entrySnippet entry
              putStrLn $ entryFilename entry
              putStrLn $ entryLanguage entry
              putStrLn $ entryDescription entry

              
entryData :: Entry -> String
entryData entry = head $ lines $ show (FmtEntry entry)
       

  
-- | Handle the search command
handleSearch :: TestableMonadIO m => SearchOptions -> m ()
handleSearch searchOpts = do
  db <- DB.load
  case db of
    (Error err) ->
      putStrLn "Failed to load DB"
    (Success db) ->
      let
        entries = DB.findAll (matchedByAllQueries (searchOptTerms searchOpts)) db
      in
        case entries of
          [] -> putStrLn "No entries found"
          _ -> putStrLn $ (unlines . L.map entryData) entries

entryVars :: Int -> String -> AddOptions -> Entry
entryVars id snippet addOpts =
  Entry
  { entryId = id
  , entrySnippet = snippet
  , entryFilename = addOptFilename addOpts
  , entryLanguage = addOptLanguage addOpts
  , entryDescription = addOptDescription addOpts
  , entryTags = addOptTags addOpts
  }
-- | Handle the add command
handleAdd :: TestableMonadIO m => AddOptions -> m ()
handleAdd addOpts = do
  fileContent <- readFile (addOptFilename addOpts)
  loadedDB <- DB.load

  case loadedDB of
   
    (Success db) ->
      let
        existingEntry = DB.findFirst (\entry -> entry == entryVars (entryId entry) fileContent addOpts) db
      in
        case existingEntry of
          Just entry -> do
                putStrLn "Entry with this content already exists: "
                putStrLn $ entryData entry
          Nothing -> do
                    DB.modify (DB.insertWith (\id -> makeEntry id fileContent addOpts))
                    return ()
    (Error err) ->
      putStrLn "Failed to load DB."
  where
    makeEntry :: Int -> String -> AddOptions -> Entry
    makeEntry id snippet addOpts =
      Entry
        { entryId = id,
          entrySnippet = snippet,
          entryFilename = addOptFilename addOpts,
          entryLanguage = addOptLanguage addOpts,
          entryDescription = addOptDescription addOpts,
          entryTags = addOptTags addOpts
        }
   

-- | Dispatch the handler for each command
run :: TestableMonadIO m => Args -> m ()
run (Add addOpts) = handleAdd addOpts
run (Search searchOpts) = handleSearch searchOpts
run (Get getOpts) = handleGet getOpts
run Init = handleInit
run Help = putStrLn usageMsg

main :: IO ()
main = do
  args <- getArgs
  let parsed = parseArgs args
  case parsed of
    (Error err) -> Prelude.putStrLn usageMsg
    (Success args) -> run args
