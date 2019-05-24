insert into token (site, data) values ('telegram', '__skip__');
insert into token (site, data) values ('adminChatId', '1, 1');
insert into telegram_registered_chat (chat_id) values (1);
insert into telegram_chat (chat_id, username, type, message_id) values (123, 'asdf', 'private', 123);
insert into twitter_keyword (id, username, keyword, status) values (1, 'realDonaldTrump', 'test1', 'ACTIVATED');
insert into twitter_keyword (id, username, keyword, status) values (2, 'realDonaldTrump', 'TeSt2', 'ACTIVATED');
insert into twitter_keyword (id, username, keyword, status) values (3, 'realDonaldTrump', 'TEST3', '');
insert into twitter_keyword (id, username, keyword, status) values (4, 'TEST4', 'TEST4', 'ACTIVATED');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -1, 'Shanghai', '2019-05-15 15:00:00+08', 170, '[{"type":"PM2.5","concentration":21.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":21.0,"AQI":70}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -2, 'Shanghai', '2019-05-15 16:00:00+08', 153, '[{"type":"PM2.5","concentration":13.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":13.0,"AQI":53}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -3, 'Shanghai', '2019-05-15 17:00:00+08', 153, '[{"type":"PM2.5","concentration":13.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":13.0,"AQI":53}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -4, 'Shanghai', '2019-05-15 18:00:00+08',  64, '[{"type":"PM2.5","concentration":18.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":18.0,"AQI":64}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -5, 'Shanghai', '2019-05-15 19:00:00+08',  61, '[{"type":"PM2.5","concentration":17.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":17.0,"AQI":61}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -6, 'Shanghai', '2019-05-15 20:00:00+08', 168, '[{"type":"PM2.5","concentration":20.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":20.0,"AQI":68}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -7, 'Shanghai', '2019-05-15 21:00:00+08',  72, '[{"type":"PM2.5","concentration":22.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":22.0,"AQI":72}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -8, 'Shanghai', '2019-05-15 22:00:00+08', 157, '[{"type":"PM2.5","concentration":15.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":15.0,"AQI":57}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values ( -9, 'Shanghai', '2019-05-15 23:00:00+08', 157, '[{"type":"PM2.5","concentration":15.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":15.0,"AQI":57}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-10, 'Shanghai', '2099-05-16 00:00:00+08', 151, '[{"type":"PM2.5","concentration":12.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":12.0,"AQI":51}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-11, 'Shanghai', '2099-05-15 15:00:00+08', 70, '[{"type":"PM2.5","concentration":21.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":21.0,"AQI":70}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-12, 'Shanghai', '2099-05-15 16:00:00+08', 53, '[{"type":"PM2.5","concentration":13.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":13.0,"AQI":53}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-13, 'Shanghai', '2099-05-15 17:00:00+08', 53, '[{"type":"PM2.5","concentration":13.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":13.0,"AQI":53}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-14, 'Shanghai', '2099-05-15 18:00:00+08', 64, '[{"type":"PM2.5","concentration":18.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":18.0,"AQI":64}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-15, 'Shanghai', '2099-05-15 19:00:00+08', 61, '[{"type":"PM2.5","concentration":17.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":17.0,"AQI":61}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-16, 'Shanghai', '2099-05-15 20:00:00+08', 68, '[{"type":"PM2.5","concentration":20.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":20.0,"AQI":68}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-17, 'Shanghai', '2099-05-15 21:00:00+08', 72, '[{"type":"PM2.5","concentration":22.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":22.0,"AQI":72}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-18, 'Shanghai', '2099-05-15 22:00:00+08', 57, '[{"type":"PM2.5","concentration":15.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":15.0,"AQI":57}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-19, 'Shanghai', '2099-05-15 23:00:00+08', 57, '[{"type":"PM2.5","concentration":15.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":15.0,"AQI":57}');
insert into air_data (id, city, message_time, aqi, pollutants, message) values (-20, 'Shanghai', '2099-05-16 00:00:00+08', 51, '[{"type":"PM2.5","concentration":12.0}]', '{"comment":"Moderate (at 24-hour exposure at this level)","PM2.5":12.0,"AQI":51}');
