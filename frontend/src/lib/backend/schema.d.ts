/**
 * This file was auto-generated by openapi-typescript.
 * Do not make direct changes to the file.
 */

export interface paths {
    "/review/{reviewId}/recommend/{memberId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        /** 리뷰 추천 */
        put: operations["recommendReview"];
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{reviewId}/comments/{id}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        /** 리뷰 댓글 수정 */
        put: operations["putComment"];
        post?: never;
        /** 리뷰 댓글 삭제 */
        delete: operations["delete"];
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{reviewId}/comments/{id}/recommend/{memberId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        /** 리뷰 댓글 추천 */
        put: operations["recommendComment"];
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{id}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        /** 리뷰 수정 */
        put: operations["putReviews"];
        post?: never;
        /** 리뷰 삭제 */
        delete: operations["deleteReviews"];
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/members/mine": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 회원 정보 조회 */
        get: operations["getMyProfile"];
        /** 회원 정보 수정 */
        put: operations["updateMyProfile"];
        post?: never;
        /** 회원 탈퇴 */
        delete: operations["delete_1"];
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/members/mine/password": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        /** 비밀번호 변경 */
        put: operations["changePassword"];
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 리뷰 목록 */
        get: operations["getReviews"];
        put?: never;
        /** 리뷰 추가 */
        post: operations["postReview"];
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{reviewId}/comments": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 리뷰 댓글 목록 조회 */
        get: operations["getComments"];
        put?: never;
        /** 리뷰 댓글 생성 */
        post: operations["postComment"];
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/members": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        put?: never;
        /** 회원가입 */
        post: operations["join"];
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/members/logout": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        put?: never;
        /** 로그아웃 */
        post?: never;
        delete: operations["logout"];
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/members/login": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        put?: never;
        /** 로그인 */
        post: operations["login"];
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/book/{id}/favorite": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get?: never;
        put?: never;
        /** 도서 찜 하기 */
        post: operations["favoriteBook"];
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{userId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 특정 유저의 리뷰 목록 조회 */
        get: operations["getUserReviews"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{reviewId}/comments/{commentId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 대댓글 조회 */
        get: operations["getReplies"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/{reviewId}/comments/review/comments/{userId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 댓글 검색 */
        get: operations["getUserComment"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/review/comments/{userId}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        get: operations["getUserComment_1"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/book": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 도서 제목 검색 */
        get: operations["searchTitleBooks"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/book/{id}": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 도서 상세 조회 */
        get: operations["searchDetailBook"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/book/list": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 도서 조회 */
        get: operations["searchAllBooks"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
    "/book/favorite": {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        /** 도서 찜 목록 */
        get: operations["searchFavoriteBooks"];
        put?: never;
        post?: never;
        delete?: never;
        options?: never;
        head?: never;
        patch?: never;
        trace?: never;
    };
}
export type webhooks = Record<string, never>;
export interface components {
    schemas: {
        GenericResponseReviewsDTO: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["ReviewsDTO"];
            success?: boolean;
        };
        MemberDto: {
            password?: string;
            email: string;
            nickname: string;
            /** Format: int32 */
            gender?: number;
            /** Format: date */
            birth?: string;
            /** Format: int64 */
            id?: number;
            username: string;
            password1: string;
            password2: string;
        };
        ReviewCommentDto: {
            /** Format: int64 */
            id?: number;
            /** Format: int64 */
            reviewId?: number;
            /** Format: int64 */
            userId: number;
            comment: string;
            /** Format: int64 */
            parentId?: number;
            /** Format: int32 */
            depth?: number;
            recommend?: components["schemas"]["MemberDto"][];
            /** Format: date-time */
            createdAt?: string;
            /** Format: date-time */
            modifiedAt?: string;
        };
        ReviewsDTO: {
            /** Format: int64 */
            id?: number;
            /** Format: int64 */
            bookId: number;
            /** Format: int64 */
            userId: number;
            content: string;
            /** Format: int32 */
            rating: number;
            reviewCommentDtos?: components["schemas"]["ReviewCommentDto"][];
            recommendMemberDtos?: components["schemas"]["MemberDto"][];
            /** Format: date-time */
            createdAt?: string;
            /** Format: date-time */
            modifiedAt?: string;
        };
        GenericResponseReviewCommentDto: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["ReviewCommentDto"];
            success?: boolean;
        };
        MineDto: {
            email: string;
            nickname: string;
            /** Format: int32 */
            gender?: number;
            /** Format: date */
            birth?: string;
        };
        GenericResponseMemberDto: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["MemberDto"];
            success?: boolean;
        };
        PasswordChangeDto: {
            currentPassword: string;
            newPassword: string;
        };
        GenericResponseString: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: string;
            success?: boolean;
        };
        LoginDto: {
            password: string;
            username: string;
        };
        FavoriteDTO: {
            memberUsername?: string;
            /** Format: int64 */
            bookId?: number;
        };
        GenericResponseListReviewsDTO: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["ReviewsDTO"][];
            success?: boolean;
        };
        GenericResponseListReviewCommentDto: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["ReviewCommentDto"][];
            success?: boolean;
        };
        BookSimpleDTO: {
            /** Format: int64 */
            id?: number;
            title?: string;
            author?: string;
            image?: string;
        };
        GenericResponseListBookSimpleDTO: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["BookSimpleDTO"][];
            success?: boolean;
        };
        BookDTO: {
            /** Format: int64 */
            id?: number;
            title?: string;
            author?: string;
            description?: string;
            image?: string;
            /** Format: int32 */
            favoriteCount?: number;
        };
        GenericResponseBookDTO: {
            /** Format: date-time */
            timestamp?: string;
            message?: string;
            data?: components["schemas"]["BookDTO"];
            success?: boolean;
        };
        PasswordDto: {
            password: string;
        };
    };
    responses: never;
    parameters: never;
    requestBodies: never;
    headers: never;
    pathItems: never;
}
export type $defs = Record<string, never>;
export interface operations {
    recommendReview: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
                memberId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewsDTO"];
                };
            };
        };
    };
    putComment: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
                id: number;
            };
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["ReviewCommentDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewCommentDto"];
                };
            };
        };
    };
    delete: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
                id: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewCommentDto"];
                };
            };
        };
    };
    recommendComment: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
                id: number;
                memberId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewCommentDto"];
                };
            };
        };
    };
    putReviews: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                id: number;
            };
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["ReviewsDTO"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewsDTO"];
                };
            };
        };
    };
    deleteReviews: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                id: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewsDTO"];
                };
            };
        };
    };
    getMyProfile: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseMemberDto"];
                };
            };
        };
    };
    updateMyProfile: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["MineDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseMemberDto"];
                };
            };
        };
    };
    delete_1: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["PasswordDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    changePassword: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["PasswordChangeDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    getReviews: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewsDTO"];
                };
            };
        };
    };
    postReview: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["ReviewsDTO"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    getComments: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewCommentDto"];
                };
            };
        };
    };
    postComment: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                reviewId: number;
            };
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["ReviewCommentDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseReviewCommentDto"];
                };
            };
        };
    };
    join: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["MemberDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    logout: {
        parameters: {
            query?: never;
            header: {
                Authorization: string;
            };
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    login: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["LoginDto"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    favoriteBook: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody: {
            content: {
                "application/json": components["schemas"]["FavoriteDTO"];
            };
        };
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseString"];
                };
            };
        };
    };
    getUserReviews: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                userId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewsDTO"];
                };
            };
        };
    };
    getReplies: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                commentId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewCommentDto"];
                };
            };
        };
    };
    getUserComment: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                userId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewCommentDto"];
                };
            };
        };
    };
    getUserComment_1: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                userId: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListReviewCommentDto"];
                };
            };
        };
    };
    searchTitleBooks: {
        parameters: {
            query: {
                title: string;
            };
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListBookSimpleDTO"];
                };
            };
        };
    };
    searchDetailBook: {
        parameters: {
            query?: never;
            header?: never;
            path: {
                id: number;
            };
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseBookDTO"];
                };
            };
        };
    };
    searchAllBooks: {
        parameters: {
            query?: {
                sortBy?: string;
                direction?: string;
            };
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListBookSimpleDTO"];
                };
            };
        };
    };
    searchFavoriteBooks: {
        parameters: {
            query?: never;
            header?: never;
            path?: never;
            cookie?: never;
        };
        requestBody?: never;
        responses: {
            /** @description OK */
            200: {
                headers: {
                    [name: string]: unknown;
                };
                content: {
                    "application/json;charset=UTF-8": components["schemas"]["GenericResponseListBookSimpleDTO"];
                };
            };
        };
    };
}
