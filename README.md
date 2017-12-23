+ 2017年12月20日
    + API初始化

## 问卷

+ Data
    + Quizs - 问卷类
        + title (String) - 问卷标题
        + description (String) - 问卷描述
        + backImg (String) - 问卷背景图
        + state (int) - 问卷状态 0：待发布，1：发布中，2，结束发布
        + questionNum (int) - 题目个数
        + enabled (int) - 是否可用
    + Questions - 试题类
        + quizId (Long) - 问卷id
        + questionTitle (String) - 试题标题
        + type (int) - 类型，1：单选 2：多选
        + displayOrder (int) - 排序
        + enabled (int) - 是否可用
        + rightNum (int) - 答对此题的人数
        + allNum (int) - 答过此题的人数
        + ratio (float) - 正确率
    + Options - 选项类
        + questionId (Long) - 试题id
        + optionTitle (String) - 选项标题
        + isCorrect (int) - 是否正确选项
        + enabled (int) - 是否可用
        + answerCount (int) - 选择此选项的人数
    + Answers - 答案类
        + sessionId (Long) - 会话id
        + questionId (Long) - 试题id
        + answers (String) - 答案（多选题以逗号分隔）
        + isRight (int) - 是否答对 0/1：否/是
        + answersList (int[]) - 答案 （答案数组）
        + correctOptions (int[]) - 正确选项
    + QuizSession - 会话类
        + sessionCode (String) - 会话编码
        + quizId (Long) - 试卷id
        + answerNum (int) - 答题个数
        + rightNum (int) - 答对个数
        + allDone (int) - 是否答完
        + enabled (int) - 是否可用
        + score (float) - 得分
    + QuizCount - 得分统计类
        + quizId (Long) - 问卷id
        + peoples (int) - 答题人数
        + first (int) - 0-9%
        + second (int) - 10-19%
        + third (int) - 20-29%
        + fourth (int) - 30-39%
        + fifth (int) - 40-49%
        + sixth (int) - 50-59%
        + seventh (int) - 60-69%
        + eighth (int) - 70-79%
        + ninth (int) - 80-89%
        + tenth (int) - 90-100%
        + enabled (int) - 是否可用

## 定制试卷

### 列表 [GET] /quizs?filter[state]=1
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Parameters
    + state
    + title
+ Response 200 (application/json)

        {
          "meta": {
            "totalPages": 1,
            "totalElements": 2,
            "size": 10,
            "number": 1,
            "numberOfElements": 2,
            "first": true,
            "last": true,
            "sort": null
          },
          "links": {
            "self": "/quizs?page[number]=1&page[size]=10",
            "first": "/quizs?page[number]=1&page[size]=10",
            "last": "/quizs?page[number]=1&page[size]=10"
          },
          "data": [
            {
              "id": 5,
              "enabled": 1,
              "creator": 1031,
              "modifier": 1031,
              "created": "2017-12-19 14:41:52",
              "modified": "2017-12-19 17:45:49",
              "title": "第一个问卷修改",
              "backImg": "image.jpg",
              "state": 0,
              "questionNum": 1
            },
            {
              "id": 6,
              "enabled": 1,
              "creator": 1031,
              "modifier": 1031,
              "created": "2017-12-20 14:18:07",
              "modified": "2017-12-20 14:48:53",
              "title": "第二个问卷",
              "backImg": "image.jpg",
              "state": 1,
              "questionNum": 2
            }
          ]
        }

### 详情 [GET] /quizs/{id}/admin
+ Description
    + [MUST] Authenticated
    + [MUST] 只有本人和管理员可以访问
+ Response 200 (application/json)
    
        {
          "data": {
            "id": 6,
            "enabled": 1,
            "creator": 1031,
            "modifier": 1031,
            "created": "2017-12-20 14:18:07",
            "modified": "2017-12-20 14:48:53",
            "title": "第二个问卷",
            "backImg": "image.jpg",
            "state": 1,
            "questionNum": 2,
            "questions": [
              {
                "id": 10,
                "enabled": 1,
                "creator": 1031,
                "modifier": 1031,
                "created": "2017-12-20 14:21:02",
                "modified": "2017-12-20 14:48:53",
                "quizId": 6,
                "questionTitle": "2题目1",
                "type": 1,
                "displayOrder": 1,
                "options": [
                  {
                    "id": 22,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:21:02",
                    "modified": "2017-12-20 14:48:53",
                    "questionId": 10,
                    "optionTitle": "选项1",
                    "isCorrect": 0,
                    "answerCount": 0
                  },
                  {
                    "id": 36,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:26:02",
                    "modified": "2017-12-20 14:48:53",
                    "questionId": 10,
                    "optionTitle": "选项2",
                    "isCorrect": 0,
                    "answerCount": 0
                  },
                  {
                    "id": 37,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:26:02",
                    "modified": "2017-12-20 14:48:53",
                    "questionId": 10,
                    "optionTitle": "选项3",
                    "isCorrect": 1,
                    "answerCount": 0
                  }
                ],
                "rightNum": 0,
                "allNum": 0
              },
              {
                "id": 13,
                "enabled": 1,
                "creator": 1031,
                "modifier": 1031,
                "created": "2017-12-20 14:25:04",
                "modified": "2017-12-20 14:48:53",
                "quizId": 6,
                "questionTitle": "2题目2",
                "type": 2,
                "displayOrder": 2,
                "options": [
                  {
                    "id": 43,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:48:54",
                    "modified": "2017-12-20 14:48:54",
                    "questionId": 13,
                    "optionTitle": "22222222222选项1",
                    "isCorrect": 0,
                    "answerCount": 0
                  },
                  {
                    "id": 44,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:48:54",
                    "modified": "2017-12-20 14:48:54",
                    "questionId": 13,
                    "optionTitle": "2选项2",
                    "isCorrect": 1,
                    "answerCount": 0
                  },
                  {
                    "id": 45,
                    "enabled": 1,
                    "creator": 1031,
                    "modifier": 1031,
                    "created": "2017-12-20 14:48:54",
                    "modified": "2017-12-20 14:48:54",
                    "questionId": 13,
                    "optionTitle": "2选项3",
                    "isCorrect": 1,
                    "answerCount": 0
                  }
                ],
                "rightNum": 0,
                "allNum": 0
              }
            ]
          }

### 定制/修改问卷 [POST] /quizs
+ Description
    + [MUST] Authenticated
    + id为空是添加，否则为修改
+ Parameters
    + id - 为空时添加，不为空时修改
    + title - 必填
    + description
    + backImg - 必填
    + questions - 必填
        + id - 为空时添加，不为空时修改
        + questionTitle - 必填
        + type - 必填
        + displayOrder - 必填
        + options - 必填
            + id - 为空时添加，不为空时修改(此时应保证该选项的试题id也不为空)
            + optionTitle - 必填
            + isCorrect - 必填
+ 添加Request 200 (application/json)

        {
            "data":{
                "title":"第一个问卷",
                "backImg":"image.jpg",
                "questions":[
                        {
                            "questionTitle":"题目1",
                            "type":1,
                            "displayOrder":1,
                            "options":[
                                    {
                                        "optionTitle":"选项1",
                                        "isCorrect":0
                                    },
                                    {
                                        "optionTitle":"选项2",
                                        "isCorrect":0
                                    },
                                    {
                                        "optionTitle":"选项3",
                                        "isCorrect":1
                                    }
                                    
                                ]
                        },
                        {
                            "questionTitle":"2题目2",
                            "type":2,
                            "displayOrder":2,
                            "options":[
                                    {
                                        "optionTitle":"2选项1",
                                        "isCorrect":0
                                    },
                                    {
                                        "optionTitle":"2选项2",
                                        "isCorrect":1
                                    },
                                    {
                                        "optionTitle":"2选项3",
                                        "isCorrect":1
                                    }
                                    
                                ]
                        }
                    ]
            }
        }
+ 修改Request 200 (application/json)

        {
            "data":{
                "id":6,
                "title":"第二个问卷",
                "backImg":"image.jpg",
                "questions":[
                        {
                            "id":10,
                            "questionTitle":"2题目1",
                            "type":1,
                            "displayOrder":1,
                            "options":[
                                    {
                                        "id":22,
                                        "optionTitle":"选项1",
                                        "isCorrect":0
                                    },
                                    {
                                        "id":36,
                                        "optionTitle":"选项2",
                                        "isCorrect":0
                                    },
                                    {
                                        "id":37,
                                        "optionTitle":"选项3",
                                        "isCorrect":1
                                    }
                                    
                                ]
                        },
                        {
                            "id":13,
                            "questionTitle":"2题目2",
                            "type":2,
                            "displayOrder":2,
                            "options":[
                                    {
                                        "optionTitle":"22222222222选项1",
                                        "isCorrect":0
                                    },
                                    {
                                        "optionTitle":"2选项2",
                                        "isCorrect":1
                                    },
                                    {
                                        "optionTitle":"2选项3",
                                        "isCorrect":1
                                    }
                                    
                                ]
                        }
                    ]
            }
        }
### 修改 [PATCH] /quizs/{id}
+ Description
    + [MUST] Authenticated
+ Parameters
    + title - 必填
    + description
    + backImg - 必填
    + state

+ 修改Request 200 (application/json)
    
        {
            "data":{
                "title":"第一个问卷修改",
                "backImg":"img.jpg",
                "state":1
            }
        }

### 删除 [DELETE] /quizs/{id}
+ Description
    + [MUST] Authenticated
+ Response 204 (application/json)

## 答题流程

### 详情(问卷首页) [GET] /quizs/{id}
+ Description
    + [MUST] 只有state=1的问卷才会有返回，否则404
+ Response 200 (application/json)

        {
          "data": {
            "id": 6,
            "enabled": 1,
            "creator": 1031,
            "modifier": 1031,
            "created": "2017-12-20 14:18:07",
            "modified": "2017-12-20 14:48:53",
            "title": "第二个问卷",
            "backImg": "image.jpg",
            "state": 1,
            "questionNum": 2,
            "quizCount": {
              "id": 2,
              "enabled": 1,
              "created": "2017-12-22 17:09:24",
              "modified": "2017-12-22 18:09:36",
              "quizId": 6,
              "peoples": 4,
              "first": 0,
              "second": 0,
              "third": 0,
              "fourth": 0,
              "fifth": 0,
              "sixth": 2,
              "seventh": 0,
              "eighth": 0,
              "ninth": 0,
              "tenth": 2
            }
          }
        }

### 申请sessionCode [POST] /quizSessions
+ Parameters
    + quizId - 必填
+ Request (application/json)
    
        {
            "data":{
                "quizId":6
            }
        }
+ Response 200 (application/json)
 
        {
          "data": {
            "sessionCode": "3d483a2f-e94a-446d-a96b-ecf5df5ad3fc",
            "id": 7,
            "type": "quizSession"
          }
        }

### 下一题 [GET] /questions?page[number]=1&filter[quizId]=6&filter[sessionCode]=aa321a2c-5495-4515-99ea-5690704a4b67
+ Description
    + 当已经答过该题时answers不为空，否则为空
+ Parameters
    + filter[quizId] - 必填
    + filter[sessionCode] - 必填
+ Response 200 (application/json)

        {
          "meta": {
            "totalPages": 2,
            "totalElements": 2,
            "size": 1,
            "number": 1,
            "numberOfElements": 1,
            "first": true,
            "last": false,
            "sort": [
              {
                "direction": "ASC",
                "property": "display_order",
                "ignoreCase": false,
                "nullHandling": "NATIVE",
                "ascending": true,
                "descending": false
              }
            ]
          },
          "links": {
            "self": "/questions?filter[quizId]=6&filter[sessionCode]=aa321a2c-5495-4515-99ea-5690704a4b67&page[number]=1&page[size]=1",
            "first": "/questions?filter[quizId]=6&filter[sessionCode]=aa321a2c-5495-4515-99ea-5690704a4b67&page[number]=1&page[size]=1",
            "next": "/questions?filter[quizId]=6&filter[sessionCode]=aa321a2c-5495-4515-99ea-5690704a4b67&page[number]=2&page[size]=1",
            "last": "/questions?filter[quizId]=6&filter[sessionCode]=aa321a2c-5495-4515-99ea-5690704a4b67&page[number]=2&page[size]=1"
          },
          "data": [
            {
              "id": 10,
              "enabled": 1,
              "creator": 1031,
              "modifier": 1031,
              "created": "2017-12-20 14:21:02",
              "modified": "2017-12-20 14:48:53",
              "quizId": 6,
              "questionTitle": "2题目1",
              "type": 1,
              "displayOrder": 1,
              "options": [
                {
                  "id": 22,
                  "enabled": 1,
                  "creator": 1031,
                  "modifier": 1031,
                  "created": "2017-12-20 14:21:02",
                  "modified": "2017-12-20 14:48:53",
                  "questionId": 10,
                  "optionTitle": "选项1",
                  "isCorrect": 0,
                  "answerCount": 0
                },
                {
                  "id": 36,
                  "enabled": 1,
                  "creator": 1031,
                  "modifier": 1031,
                  "created": "2017-12-20 14:26:02",
                  "modified": "2017-12-20 14:48:53",
                  "questionId": 10,
                  "optionTitle": "选项2",
                  "isCorrect": 0,
                  "answerCount": 0
                },
                {
                  "id": 37,
                  "enabled": 1,
                  "creator": 1031,
                  "modifier": 1031,
                  "created": "2017-12-20 14:26:02",
                  "modified": "2017-12-20 14:48:53",
                  "questionId": 10,
                  "optionTitle": "选项3",
                  "isCorrect": 1,
                  "answerCount": 0
                }
              ],
              "rightNum": 0,
              "allNum": 0,
              "answers": {
                "id": 8,
                "created": "2017-12-22 18:07:57",
                "modified": "2017-12-22 18:07:57",
                "sessionId": 6,
                "questionId": 10,
                "answers": "37",
                "isRight": 1,
                "correctOptions": [
                  37
                ]
              }
            }
          ]
        }
### 提交答案 [POST] /answers
+ Parameters
    + sessionCode - 必填
    + questionId - 必填
    + answersList - 必填
+ 添加Request (application/json)

        {
            "data":{
                "sessionCode":"3d483a2f-e94a-446d-a96b-ecf5df5ad3fc",
                "questionId":10,
                "answersList":[
                        44
                    ]
            }
        }
+ 添加Response 200 (application/json)
    
        {
          "data": {
            "isRight": 0,
            "correctOptions": [
              37
            ],
            "allDone": 1,
            "id": 11,
            "type": "Answers"
          }
        }

### 答题结束，查看评分 [GET] /quizSessions/score
+ Parameters
    + sessionCode - 必填
+ 添加Response 200 (application/json)

        {
          "data": {
            "id": 7,
            "enabled": 1,
            "creator": 0,
            "modifier": 0,
            "created": "2017-12-23 16:53:52",
            "modified": "2017-12-23 17:07:33",
            "sessionCode": "3d483a2f-e94a-446d-a96b-ecf5df5ad3fc",
            "quizId": 6,
            "answerNum": 2,
            "rightNum": 0,
            "allDone": 1,
            "score": 0,
            "count": {
              "id": 2,
              "enabled": 1,
              "created": "2017-12-22 17:09:24",
              "modified": "2017-12-23 17:15:57",
              "quizId": 6,
              "peoples": 5,
              "first": 1,
              "second": 0,
              "third": 0,
              "fourth": 0,
              "fifth": 0,
              "sixth": 2,
              "seventh": 0,
              "eighth": 0,
              "ninth": 0,
              "tenth": 2
            }
          }
        }

## 答题统计

### 试卷概览 [GET] /quizsCounts/quizs?page[number]=1&page[size]=1&filter[quizId]=6
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Parameters
    + filter[quizId] - 必填
+ Response 200 (application/json)

        {
          "meta": {
            "totalPages": 2,
            "totalElements": 4,
            "size": 3,
            "number": 1,
            "numberOfElements": 3,
            "first": true,
            "last": false,
            "sort": null
          },
          "links": {
            "self": "/quizsCount?filter[quizId]=6&page[number]=1&page[size]=3",
            "first": "/quizsCount?filter[quizId]=6&page[number]=1&page[size]=3",
            "next": "/quizsCount?filter[quizId]=6&page[number]=2&page[size]=3",
            "last": "/quizsCount?filter[quizId]=6&page[number]=2&page[size]=3"
          },
          "data": [
            {
              "id": 1,
              "enabled": 1,
              "creator": 0,
              "modifier": 0,
              "created": "2017-12-20 10:47:44",
              "modified": "2017-12-21 17:07:43",
              "sessionCode": "dabdc3bd-7ce2-4b1f-a7fa-d6dc8f24e6ab",
              "quizId": 6,
              "answerNum": 1,
              "rightNum": 0,
              "allDone": 0,
              "score": 0,
              "questionAnswers": [
                {
                  "questionId": 10,
                  "questionTitle": "2题目1",
                  "answers": null
                },
                {
                  "questionId": 13,
                  "questionTitle": "2题目2",
                  "answers": "43,44"
                }
              ]
            },
            {
              "id": 5,
              "enabled": 1,
              "creator": 0,
              "modifier": 0,
              "created": "2017-12-22 16:11:18",
              "modified": "2017-12-22 16:12:25",
              "sessionCode": "d35c2a76-b0f3-4adf-8dcc-b942a0d5b051",
              "quizId": 6,
              "answerNum": 2,
              "rightNum": 2,
              "allDone": 1,
              "score": 0,
              "questionAnswers": [
                {
                  "questionId": 10,
                  "questionTitle": "2题目1",
                  "answers": "37"
                },
                {
                  "questionId": 13,
                  "questionTitle": "2题目2",
                  "answers": "45,44"
                }
              ]
            },
            {
              "id": 6,
              "enabled": 1,
              "creator": 0,
              "modifier": 0,
              "created": "2017-12-22 16:13:16",
              "modified": "2017-12-22 18:08:09",
              "sessionCode": "aa321a2c-5495-4515-99ea-5690704a4b67",
              "quizId": 6,
              "answerNum": 2,
              "rightNum": 1,
              "allDone": 1,
              "score": 0,
              "questionAnswers": [
                {
                  "questionId": 10,
                  "questionTitle": "2题目1",
                  "answers": "37"
                },
                {
                  "questionId": 13,
                  "questionTitle": "2题目2",
                  "answers": "44"
                }
              ]
            }
          ]
        }

### 题目的正确率 [GET] /quizsCounts/questions/{id}
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Response 200 (application/json)

        {
          "data": {
            "id": 10,
            "enabled": 1,
            "creator": 1031,
            "modifier": 1031,
            "created": "2017-12-20 14:21:02",
            "modified": "2017-12-20 14:48:53",
            "quizId": 6,
            "questionTitle": "2题目1",
            "type": 1,
            "displayOrder": 1,
            "rightNum": 2,
            "allNum": 3,
            "ratio": "0.67"
          }
        }
### 选项统计 [GET] /quizsCounts/questions/{id}/options
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Response 200 (application/json)

        {
          "data": {
            "id": 10,
            "enabled": 1,
            "creator": 1031,
            "modifier": 1031,
            "created": "2017-12-20 14:21:02",
            "modified": "2017-12-20 14:48:53",
            "quizId": 6,
            "questionTitle": "2题目1",
            "type": 1,
            "displayOrder": 1,
            "options": [
              {
                "id": 22,
                "enabled": 1,
                "creator": 1031,
                "modifier": 1031,
                "created": "2017-12-20 14:21:02",
                "modified": "2017-12-20 14:48:53",
                "questionId": 10,
                "optionTitle": "选项1",
                "isCorrect": 0,
                "answerCount": 0
              },
              {
                "id": 36,
                "enabled": 1,
                "creator": 1031,
                "modifier": 1031,
                "created": "2017-12-20 14:26:02",
                "modified": "2017-12-20 14:48:53",
                "questionId": 10,
                "optionTitle": "选项2",
                "isCorrect": 0,
                "answerCount": 0
              },
              {
                "id": 37,
                "enabled": 1,
                "creator": 1031,
                "modifier": 1031,
                "created": "2017-12-20 14:26:02",
                "modified": "2017-12-20 14:48:53",
                "questionId": 10,
                "optionTitle": "选项3",
                "isCorrect": 1,
                "answerCount": 2
              }
            ],
            "rightNum": 0,
            "allNum": 0
          }
        }

## 试卷统计表后台接口

### 列表 [GET] /quizsCounts?filter[quizId]=6
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Parameters
    + quizId
+ Response 200 (application/json)
    
        {
          "meta": {
            "totalPages": 1,
            "totalElements": 2,
            "size": 10,
            "number": 1,
            "numberOfElements": 2,
            "first": true,
            "last": true,
            "sort": null
          },
          "links": {
            "self": "/quizsCounts?page[number]=1&page[size]=10",
            "first": "/quizsCounts?page[number]=1&page[size]=10",
            "last": "/quizsCounts?page[number]=1&page[size]=10"
          },
          "data": [
            {
              "id": 1,
              "enabled": 0,
              "quizId": 5,
              "peoples": 10,
              "first": 1,
              "second": 2,
              "third": 0,
              "fourth": 1,
              "fifth": 1,
              "sixth": 1,
              "seventh": 1,
              "eighth": 1,
              "ninth": 1,
              "tenth": 0
            },
            {
              "id": 2,
              "enabled": 1,
              "created": "2017-12-22 17:09:24",
              "modified": "2017-12-23 17:15:57",
              "quizId": 6,
              "peoples": 5,
              "first": 1,
              "second": 0,
              "third": 0,
              "fourth": 0,
              "fifth": 0,
              "sixth": 2,
              "seventh": 0,
              "eighth": 0,
              "ninth": 0,
              "tenth": 2
            }
          ]
        }

### 详情 [GET] /quizsCounts/{id}
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Response 200 (application/json)
    
        {
          "data": {
            "id": 1,
            "enabled": 0,
            "quizId": 5,
            "peoples": 10,
            "first": 1,
            "second": 2,
            "third": 0,
            "fourth": 1,
            "fifth": 1,
            "sixth": 1,
            "seventh": 1,
            "eighth": 1,
            "ninth": 1,
            "tenth": 0
          }
        }

### 增加 [POST] /quizsCounts
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Parameters
    + quizId - 必填
    + peoples
    + first
    + second
    + third
    + fourth
    + fifth
    + sixth
    + seventh
    + eighth
    + ninth
    + tenth
+ 添加Response 200 (application/json)
    
        {
            "data":{
                "quizId":6
            }
        }
+ Response 201 (application/json)

### 修改 [PATCH] /quizsCounts/{id}
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Parameters
    + quizId - 必填
    + peoples
    + first
    + second
    + third
    + fourth
    + fifth
    + sixth
    + seventh
    + eighth
    + ninth
    + tenth
+ 修改Response 200 (application/json)
    
        {
            "data":{
                "quizId":6,
                "peoples":1000,
                "first":100,
                "second":100,
                "third":100,
                "fourth":100,
                "fifth":100,
                "sixth":100,
                "seventh":100,
                "eighth":100,
                "ninth":100,
                "tenth":100
            }
        }
+ Response 200 (application/json)

### 删除 [DELETE] /quizsCounts/{id}
+ Description
    + [MUST] Authenticated
    + [MUST] ROLE_ADMIN
+ Response 204 (application/json)
