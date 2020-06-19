News App project
Project use mvvm architacture using coroutines, room database and flows with paging library

* For Pagination new paging library is used , it provides support for storing the data in chunks

PagingSource class handle the dataset sent to recyclerview , it works along with paging adapter and pagingdata class

project modules.

UI module -> Here there is an activity and two fragment, news list fragment observe the flow data and submit to adpater

Network module -> retrofit api is used for getting news

room -> data base to store the news list and page number to maintaing paging

RemoteMediatorController -> This class is core of paging concept used here , load method gets calls based on states
state are REFRESH, APPEND, PREPEND
when user scroll to next page APPEND state is called with page number

Coroutine worker is used to delete the data after 2 hours.